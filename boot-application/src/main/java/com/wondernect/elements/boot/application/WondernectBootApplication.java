package com.wondernect.elements.boot.application;

import com.wondernect.elements.boot.application.config.WondernectBootConfigProperties;
import com.wondernect.elements.boot.application.event.WondernectBootEvent;
import com.wondernect.elements.boot.application.event.WondernectBootEventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

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
@EntityScan(basePackages = {
        "com.wondernect.*"
})
@EnableJpaRepositories(basePackages = {
        "com.wondernect.*"
})
@SpringBootApplication
public abstract class WondernectBootApplication implements CommandLineRunner {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private WondernectBootConfigProperties wondernectBootConfigProperties;

    @Override
    public void run(String... args) throws Exception {
        initAfterBootFinished();
        applicationContext.publishEvent(new WondernectBootEvent(this, WondernectBootEventType.BOOT));
        System.out.println("service start success at port " + wondernectBootConfigProperties.getPort() + " .....");
    }

    public abstract void initAfterBootFinished();
}
