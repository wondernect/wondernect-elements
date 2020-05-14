package com.wondernect.elements.itext.config;

import com.wondernect.elements.property.source.WondernectPropertySourceFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Created on 2017/10/26.
 * wondernect.com
 * @author sunbeam
 */
@Component
@Primary
@PropertySource(value = {"classpath:application.properties", "classpath:application.yml", "classpath:application.yaml"}, ignoreResourceNotFound = true, factory = WondernectPropertySourceFactory.class)
@ConfigurationProperties(prefix = "wondernect.elements.itext")
public class ITextConfigProperties implements Serializable {

    private static final long serialVersionUID = -5251021268093178187L;

    private String defaultFontPath = "fonts/simsun.ttc";

    private List<String> fontPaths;

    public String getDefaultFontPath() {
        return defaultFontPath;
    }

    public void setDefaultFontPath(String defaultFontPath) {
        this.defaultFontPath = defaultFontPath;
    }

    public List<String> getFontPaths() {
        return fontPaths;
    }

    public void setFontPaths(List<String> fontPaths) {
        this.fontPaths = fontPaths;
    }
}
