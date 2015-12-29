package org.dontpanic.spanners.stubs;

import org.dontpanic.spanners.dao.SpannersDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public SpannersDAO configureSpannersDao() {
         return mock(SpannersDAO.class);
    }

    @Bean(name = "spannersDS")
    public DataSource stubDataSource() {
        return mock(DataSource.class);
    }
}
