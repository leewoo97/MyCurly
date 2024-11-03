package com.dldnwls.mycurly.infra.batch.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@RequiredArgsConstructor
@Configuration
@EnableScheduling
public class SchedularConfig {

    private final JobLauncher jobLauncher;
    private final Job seleniumCrawlerJob;

    @Scheduled(cron = "0 */2 * * * *") //2분간격으로 실행
    public void runJob() {
        try {
            jobLauncher.run(seleniumCrawlerJob, new org.springframework.batch.core.JobParameters());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
