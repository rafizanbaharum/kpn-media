package my.gov.kpn.media.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class KpnWebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/css/**").addResourceLocations("/resources/css/");
        registry.addResourceHandler("/resources/fonts/**").addResourceLocations("/resources/fonts/");
        registry.addResourceHandler("/resources/img/**").addResourceLocations("/resources/img/");
        registry.addResourceHandler("/resources/plugins/**").addResourceLocations("/resources/plugins/");
        registry.addResourceHandler("/resources/scripts/**").addResourceLocations("/resources/scripts/");
        registry.addResourceHandler("/resources/assets/**").addResourceLocations("/resources/assets/");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        super.addFormatters(registry);
    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
    }
}
