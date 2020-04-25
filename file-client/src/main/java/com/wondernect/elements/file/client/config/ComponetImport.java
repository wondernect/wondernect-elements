package com.wondernect.elements.file.client.config;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

/**
 * Created on 2017/9/28.
 * wondernect.com
 * @author sunbeam
 */
@Configuration
@Import(FdfsClientConfig.class)
// 解决jmx重复注册bean的问题
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
@EnableConfigurationProperties(FileClientConfigProperties.class)
public class ComponetImport {
    // 导入依赖组件

}
