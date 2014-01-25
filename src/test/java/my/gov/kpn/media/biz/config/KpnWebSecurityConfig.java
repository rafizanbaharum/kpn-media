package my.gov.kpn.media.biz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

/**
 * http://spring.io/blog/2013/07/03/spring-security-java-config-preview-web-security/
 */
@Configuration
public class KpnWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired(required = false)
    @Qualifier(value = "userDetailService")
    private UserDetailsService userDetailService;

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers()
                .addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/*").permitAll()
                .antMatchers("/gate/**").permitAll()
                .antMatchers("/services/**").permitAll()
                .antMatchers("/service/**").permitAll()
                .antMatchers("/dashboard").hasRole("USER")
                .antMatchers("/secure/**").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/dashboard")
                .failureUrl("/gate/in?login_error=1")
                .loginPage("/gate/in")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/index")
                .invalidateHttpSession(true);
    }

    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return new AuthenticationManagerBuilder(ObjectPostProcessor.QUIESCENT_POSTPROCESSOR)
                .userDetailsService(userDetailService)
                .passwordEncoder(new PlaintextPasswordEncoder())
                .and().build();  // user detail
    }
}
