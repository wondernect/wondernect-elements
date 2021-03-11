package com.wondernect.elements.boot.application;

import com.wondernect.elements.boot.application.config.WondernectBootApplicationConfigProperties;
import com.wondernect.elements.boot.application.config.WondernectBootServerConfigProperties;
import com.wondernect.elements.boot.application.event.WondernectBootEvent;
import com.wondernect.elements.boot.application.event.WondernectBootEventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: WondernectStarsApplication
 * Author: chenxun
 * Date: 2020-06-24 09:47
 * Description:
 */
@ComponentScans({
        @ComponentScan(basePackages = "com.wondernect.*")
})
@SpringBootApplication
public abstract class WondernectBootApplication implements CommandLineRunner {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private WondernectBootServerConfigProperties wondernectBootServerConfigProperties;

    @Autowired
    private WondernectBootApplicationConfigProperties wondernectBootApplicationConfigProperties;

    @Override
    public void run(String... args) throws Exception {
        initAfterBootFinished();
        applicationContext.publishEvent(new WondernectBootEvent(this, WondernectBootEventType.BOOT));
        System.out.println("service " + wondernectBootApplicationConfigProperties.getName() + " start success at port " + wondernectBootServerConfigProperties.getPort() + " .....");
    }

    public abstract void initAfterBootFinished();
}
