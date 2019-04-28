/*
package demo.ms.common.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;


@EntityScan({"demo.ms.common.**.*"})
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ConditionalOnMissingBean(ResourceServerConfig.class)
@Import({TokenConfig.class,JwtFilterConfig.class,SwaggerConfig.class,RestTemplateConfig.class})
@
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .cors()
            .and()
            .authorizeRequests()
            .antMatchers(
                    "/dummy/**",
                    "/info",
                    "/health",
                    "/hystrix.stream",
                    "/swagger-ui.html",
                    "/v2/api-docs",
                    "/swagger-resources",
                    "/swagger-resources/configuration/ui",
                    "/swagger-resources/configuration/security",
                    "/registry","/stomp/**"
            ).permitAll()
            .anyRequest().authenticated();
    }
}
*/
