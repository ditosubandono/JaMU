package com.smartbee.jamu.jpos.client.config;

import freemarker.cache.SoftCacheStorage;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.utility.XmlEscape;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by galihlasahido on 9/5/14.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.smartbee.jamu.jpos.client" })
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
        registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
        registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public FreeMarkerViewResolver freeMarkerViewResolver() throws ClassNotFoundException {

        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setPrefix("");
        resolver.setSuffix(".ftl");
        resolver.setCache(true);
        resolver.setExposeSpringMacroHelpers(true);
        resolver.setRequestContextAttribute("rc");
        resolver.setViewClass(Class.forName("org.springframework.web.servlet.view.freemarker.FreeMarkerView"));
        return resolver;
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("file:///Users/galihlasahido/code/jpos/jamu/client/src/dist/webapps/root/WEB-INF/template/");
        Map map = new HashMap();
        map.put("xml_escape", "fmXmlEscape");
        freeMarkerConfigurer.setFreemarkerVariables(map);
        return freeMarkerConfigurer;
    }

    @Bean
    public XmlEscape xmlEscape() {
        return new XmlEscape();
    }

    @Bean
    public SoftCacheStorage softCacheStorage() {
        return new SoftCacheStorage();
    }

    @Bean
    public BeansWrapper beansWrapper() {
        BeansWrapper beansWrapper = new BeansWrapper();
        beansWrapper.setSimpleMapWrapper(true);
        return beansWrapper;
    }

}
