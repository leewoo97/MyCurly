package com.dldnwls.mycurly.infra.batch.crawler.jobconfig;

import com.dldnwls.mycurly.infra.batch.crawler.SeleniumCrawler;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.builder.SimpleJobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@RequiredArgsConstructor
@Configuration
public class SeleniumCrawlerJobConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public Job seleniumCrawlerJob() {
        JobBuilder jobBuilder = new JobBuilder("seleniumCrawlerJob", jobRepository);
        SimpleJobBuilder job = jobBuilder.start(crawlStep())
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(JobExecution jobExecution) {
                        System.out.println("Crawler Job 시작");
                    }

                    @Override
                    public void afterJob(JobExecution jobExecution) {
                        System.out.println("Crawler Job 완료");
                    }
                });
        return job.build();
    }

    @Bean
    public Step crawlStep() {
        StepBuilder stepBuilder = new StepBuilder("crawlStep", jobRepository);
        return stepBuilder.tasklet((contribution, chunkContext) -> {
                    SeleniumCrawler.main(new String[]{}); // SeleniumCrawler 실행
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

}
