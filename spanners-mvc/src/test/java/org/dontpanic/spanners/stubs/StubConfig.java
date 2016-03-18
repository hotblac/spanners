package org.dontpanic.spanners.stubs;

import org.dontpanic.spanners.dao.SpannersService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;

import static org.mockito.Mockito.mock;

/**
 * Stubs for services required by application context
 * User: Stevie
 * Date: 27/10/13
 */
@Configuration
public class StubConfig {

    @Bean
    public SpannersService stubSpannersDao() {
         return mock(SpannersService.class);
    }

    @Bean(name = "spannersDS")
    public DataSource stubDataSource() {
        return mock(DataSource.class);
    }

    @Bean
    public UserDetailsManager stubUserDetailsManager() {
        return mock(UserDetailsManager.class);
    }

    @Bean
    public PasswordEncoder stubPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
