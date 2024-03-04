package com.example.secondsql.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.secondsql.repository",
        entityManagerFactoryRef = "paymentEntityManagerFactory",
        transactionManagerRef = "getPlatformTxMgr")
public class BooksConfig {
    @Bean
    @ConfigurationProperties(prefix = "payment.datasource")
    DataSourceProperties getDataSourceProperty() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "book.datasource")
    DataSource getDataSource() {
        return getDataSourceProperty().initializeDataSourceBuilder().type(BasicDataSource.class).build();
    }

    @Bean(name = "paymentEntityManagerFactory")
    @Primary
    LocalContainerEntityManagerFactoryBean bookEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(getDataSource()).packages("com.example.secondsql.entity").build();
    }

    @Bean
    @Primary
    PlatformTransactionManager getPlatformTxMgr(
            final @Qualifier("paymentEntityManagerFactory") LocalContainerEntityManagerFactoryBean bookEntityManagerFactory) {
        return new JpaTransactionManager(bookEntityManagerFactory.getObject());
    }

}

