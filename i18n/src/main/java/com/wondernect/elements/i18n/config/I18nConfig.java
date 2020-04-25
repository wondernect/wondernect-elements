package com.wondernect.elements.i18n.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
@EnableConfigurationProperties(I18nConfigProperties.class)
public class I18nConfig implements WebMvcConfigurer {

    @Autowired
    private I18nConfigProperties i18nConfigProperties;
	
	@Autowired
	private MessageSource messageSource;

	@Bean
	public LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.setValidationMessageSource(messageSource);
		return localValidatorFactoryBean;
	}

	@Override  
    public Validator getValidator() {  
        return validator();
    }
	
	@Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        if ("TAIWAN".equalsIgnoreCase(i18nConfigProperties.getDefaultLocale())) {
            slr.setDefaultLocale(Locale.TAIWAN);
        } else if ("US".equalsIgnoreCase(i18nConfigProperties.getDefaultLocale())) {
            slr.setDefaultLocale(Locale.US);
        } else {
            slr.setDefaultLocale(Locale.CHINA);
        }
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName(i18nConfigProperties.getLocaleParam());
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
