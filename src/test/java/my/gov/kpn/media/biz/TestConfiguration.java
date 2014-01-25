package my.gov.kpn.media.biz;

import my.gov.kpn.media.biz.config.TestDatasourceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = {"my.gov.kpn.media.core", "my.gov.kpn.media.biz"})
@Import({
        TestDatasourceConfig.class // TODO
//        TestSecurityConfig.class
})
@PropertySource("classpath:app.properties")
public class TestConfiguration {

}
