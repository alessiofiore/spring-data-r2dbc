package org.madbit.r2dbc.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "org.madbit.r2dbc.repository")
public class JpaConfig {

    @Bean
    public DataSource dataSource(Environment env) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(env.getProperty("spring.datasource.url"));
        config.setUsername(env.getProperty("spring.datasource.username"));
        config.setPassword(env.getProperty("spring.datasource.password"));
        config.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        config.setConnectionTimeout(Long.valueOf(env.getProperty("spring.datasource.hikari.connectionTimeout")));
        config.setMaximumPoolSize(Integer.valueOf(env.getProperty("spring.datasource.hikari.maximumPoolSize")));
        config.setPoolName(env.getProperty("spring.datasource.hikari.poolName"));
        config.setIdleTimeout(Long.valueOf(env.getProperty("spring.datasource.hikari.idleTimeout")));
        config.setMaxLifetime(Long.valueOf(env.getProperty("spring.datasource.hikari.maxLifetime")));
        config.setMinimumIdle(Integer.valueOf(env.getProperty("spring.datasource.hikari.minimumIdle")));

        config.addDataSourceProperty("implicitCachingEnabled", "true");
        config.addDataSourceProperty("fastConnectionFailoverEnabled", "true");
        return new HikariDataSource(config);
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(Environment env) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource(env));
        em.setPackagesToScan("org.madbit.r2dbc");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties(env));
        em.afterPropertiesSet();
        return em;
    }

    private Properties additionalProperties(Environment env) {
        Properties properties = new Properties();
        properties.setProperty("hibernate.default_schema",
                env.getProperty("spring.jpa.properties.hibernate.default_schema"));
        properties.setProperty("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
        properties.setProperty("hibernate.show_sql", env.getProperty("spring.jpa.properties.hibernate.show_sql"));
        properties.setProperty("hibernate.format_sql", env.getProperty("spring.jpa.properties.hibernate.format_sql"));
        properties.setProperty("hibernate.jdbc.time_zone", env.getRequiredProperty("spring.jpa.properties.hibernate.jdbc.time_zone"));
        return properties;
    }
}
