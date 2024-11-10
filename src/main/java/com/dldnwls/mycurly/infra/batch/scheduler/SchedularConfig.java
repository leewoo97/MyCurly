package com.dldnwls.mycurly.infra.batch.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@RequiredArgsConstructor
@Configuration
@EnableScheduling
public class SchedularConfig {

    private final JobLauncher jobLauncher;
    private final JobRepository jobRepository;
    private final Job seleniumCrawlerJob;

    @Scheduled(cron = "0 */2 * * * *") //2분간격으로 실행
    public void runJob() {
        try {
            // 이미 실행된 JobInstance가 있는지 확인
            boolean jobInstanceExists = jobRepository.isJobInstanceExists(
                    "seleniumCrawlerJob",
                    new JobParameters()
            );

            if (jobInstanceExists) {
                System.out.println("Job is already running or completed. Skipping execution.");
                return; // 실행 중이거나 완료된 Job이 있다면 실행하지 않음
            }

            // Job 실행
            jobLauncher.run(seleniumCrawlerJob, new JobParameters());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
