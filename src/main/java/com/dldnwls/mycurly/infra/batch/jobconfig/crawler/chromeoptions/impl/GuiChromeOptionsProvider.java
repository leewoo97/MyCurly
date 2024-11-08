package com.dldnwls.mycurly.infra.batch.jobconfig.crawler.chromeoptions.impl;

import com.dldnwls.mycurly.infra.batch.jobconfig.crawler.chromeoptions.ChromeOptionsProvider;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class GuiChromeOptionsProvider implements ChromeOptionsProvider {

    @Override
    public ChromeOptions getOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        return options;
    }
}
