package com.dldnwls.mycurly.infra.batch.jobconfig.crawler.chromeoptions.impl;

import com.dldnwls.mycurly.infra.batch.jobconfig.crawler.chromeoptions.ChromeOptionsProvider;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod") //운영환경에서만 활성화
public class HeadlessChromeOptionsProvider implements ChromeOptionsProvider {

    @Override
    public ChromeOptions getOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");  // 헤드리스 모드
        options.addArguments("--no-sandbox");  // 권한 문제 방지
        options.addArguments("--disable-dev-shm-usage");  // 공유 메모리 이슈 방지
        options.addArguments("--remote-debugging-port=9222");  // 디버깅 포트
        return options;
    }
}
