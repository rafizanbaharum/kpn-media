package my.gov.kpn.media.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * http://spring.io/blog/2013/07/03/spring-security-java-config-preview-web-security/
 */
@Configuration
public class TestSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired(required = false)
    @Qualifier(value = "userDetailService")
    private UserDetailsService userDetailService;

    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return new AuthenticationManagerBuilder(ObjectPostProcessor.QUIESCENT_POSTPROCESSOR)
                .userDetailsService(userDetailService)
                .passwordEncoder(new PlaintextPasswordEncoder())
                .and().build();  // user detail
    }
}
