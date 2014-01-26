package my.gov.kpn.media.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.extras.springsecurity3.dialect.SpringSecurityDialect;
import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.spring3.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

@Configuration
public class KpnWebViewConfig {

    public static final String TEMPLATE_ROOT = "/pages/";
    public static final String TEMPLATE_MODE = "HTML5";
    public static final String TEMPLATE_SUFFIX_HTML = ".html";
    public static final String TEMPLATE_DIRECTORY_COMMON = "fragments/common";
    public static final String TEMPLATE_DIRECTORY_COMPONENT = "fragments/component";
    public static final String TEMPLATE_ALIAS_COMMON = "common";
    public static final String TEMPLATE_ALIAS_COMPONENT = "component";

    @Bean
    public TemplateResolver templateResolver() {
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
        templateResolver.setPrefix(TEMPLATE_ROOT);
        templateResolver.setSuffix(TEMPLATE_SUFFIX_HTML);
        templateResolver.setTemplateMode(TEMPLATE_MODE);
        templateResolver.addTemplateAlias(TEMPLATE_ALIAS_COMMON, TEMPLATE_DIRECTORY_COMMON);
        templateResolver.addTemplateAlias(TEMPLATE_ALIAS_COMPONENT, TEMPLATE_DIRECTORY_COMPONENT);
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    @Bean
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }


    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.addDialect("sec", springSecurityDialect());
        return templateEngine;
    }

    @Bean
    public ViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setOrder(1);
        return viewResolver;
    }

    @Bean
    public ViewResolver jspViewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("message");
        return messageSource;
    }
}
