package my.gov.kpn.media.web;

import my.gov.kpn.media.web.config.KpnWebDatasourceConfig;
import my.gov.kpn.media.web.config.KpnWebMvcConfig;
import my.gov.kpn.media.web.config.KpnWebSecurityConfig;
import my.gov.kpn.media.web.config.KpnWebViewConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = "my.gov.kpn.media")
@Import({
        KpnWebDatasourceConfig.class,
        KpnWebSecurityConfig.class,
        KpnWebMvcConfig.class,
        KpnWebViewConfig.class
})
@PropertySource("classpath:app.properties")
public class AppConfiguration {

}
