package by.triumgroup.recourse.configuration;

import by.triumgroup.recourse.configuration.security.*;
import by.triumgroup.recourse.service.UserService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

@Configuration
@Import({
        PersistenceConfiguration.class,
        ServiceConfiguration.class,
        ControllerConfiguration.class,
        SecurityConfiguration.class,
        ResourceServerConfiguration.class,
        AuthorizationServerConfiguration.class,
        MethodSecurityConfiguration.class
})
@EnableAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class,
        org.springframework.boot.autoconfigure.security.SecurityFilterAutoConfiguration.class,
        org.springframework.boot.autoconfigure.security.FallbackWebSecurityAutoConfiguration.class,
        org.springframework.boot.autoconfigure.security.oauth2.OAuth2AutoConfiguration.class
})
public class MainConfiguration extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MainConfiguration.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        return new JdbcUserDetailsService(userService);
    }

    @Bean
    public TokenEnhancer tokenEnhancer(UserService userService) {
        return new CustomTokenEnhancer(userService);
    }

}