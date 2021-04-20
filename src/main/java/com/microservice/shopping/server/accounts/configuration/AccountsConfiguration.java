package com.microservice.shopping.server.accounts.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;
import java.util.logging.Logger;

@Configuration
@ComponentScan
@EntityScan("com.microservice.shopping.server.accounts")
@EnableJpaRepositories("com.microservice.shopping.server.accounts")
@PropertySource("classpath:db-config.properties")
public class AccountsConfiguration {
    protected Logger logger;

    public AccountsConfiguration() {
        logger = Logger.getLogger(getClass().getName());
    }

    /**
     * Creates an in-memory database from the schema.sql and data.sql file
     */
    @Bean
    public DataSource dataSource() {
        logger.info("dataSource() invoked");

        // Create an in-memory H2 relational database containing some demo
        // accounts.
        DataSource dataSource = (new EmbeddedDatabaseBuilder()).addScript("classpath:db/schema.sql")
                .addScript("classpath:db/data.sql").build();

        logger.info("dataSource = " + dataSource);

        return dataSource;
    }
}
