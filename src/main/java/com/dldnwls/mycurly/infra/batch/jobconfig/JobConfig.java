package com.dldnwls.mycurly.infra.batch.jobconfig;

import com.dldnwls.mycurly.domain.inventory.entity.Inventory;
import com.dldnwls.mycurly.infra.batch.jobconfig.crawler.SeleniumCrawler;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.builder.SimpleJobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.transaction.PlatformTransactionManager;

@RequiredArgsConstructor
@Configuration
public class JobConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final EntityManagerFactory entityManagerFactory;
    private final SeleniumCrawler seleniumCrawler;

    @Value("${path.productDetails}")
    private String directoryPath;

    @Bean
    public Job seleniumCrawlerJob() {
        JobBuilder jobBuilder = new JobBuilder("seleniumCrawlerJob", jobRepository);

        SimpleJobBuilder job = jobBuilder.start(crawlStep(seleniumCrawler))
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(JobExecution jobExecution) {
                        System.out.println("Crawler Job 시작");
                    }


                    @Override
                    public void afterJob(JobExecution jobExecution) {
                        System.out.println("Crawler Job 완료");
                    }
                })
                .next(updateInventoryStep());

        return job.build();
    }

    @Bean
    public Step crawlStep(SeleniumCrawler seleniumCrawler) {
        StepBuilder stepBuilder = new StepBuilder("crawlStep", jobRepository);
        return stepBuilder.tasklet((contribution, chunkContext) -> {
                    seleniumCrawler.runCrawler(); // SeleniumCrawler 실행
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

    @Bean
    public Step updateInventoryStep() {
        StepBuilder stepBuilder = new StepBuilder("updateInventoryStep", jobRepository);
        return stepBuilder.<Inventory, Inventory>chunk(10, transactionManager)
                .reader(updateInventoryItemReader())
                .writer(updateInventoryItemWriter(entityManagerFactory))
                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<Inventory> updateInventoryItemReader() {
        FlatFileItemReader<Inventory> reader = new FlatFileItemReader<>();

        // 파일 시스템 경로를 명확히 지정
        Resource resource = new FileSystemResource(directoryPath);
        reader.setResource(resource);
        System.out.println("Resource Path: " + resource);

        // Check if resource exists
        if (!resource.exists()) {
            throw new IllegalArgumentException("Resource file not found: " + resource);
        }

        reader.setLinesToSkip(1);  // Header line
        reader.setLineMapper(lineMapper());

        return reader;
    }

    private LineMapper<Inventory> lineMapper() {
        DefaultLineMapper<Inventory> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setNames("name", "price", "deliveryMethod", "gram", "link");

        BeanWrapperFieldSetMapper<Inventory> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Inventory.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        return lineMapper;
    }

    @Bean
    public JpaItemWriter<Inventory> updateInventoryItemWriter(EntityManagerFactory entityManagerFactory) {
        JpaItemWriter<Inventory> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }

}

