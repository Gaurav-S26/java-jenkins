package com.example.sql.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;


import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.sql.repository",
        entityManagerFactoryRef = "orderEntityManagerFactory",
        transactionManagerRef = "getOrderPTM")
public class ProductConfig {


    @Bean
    @ConfigurationProperties(prefix = "product.datasource")
    DataSourceProperties getDatasourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("product.datasource.configuration")
    DataSource getDatasource() {
        return getDatasourceProperties().initializeDataSourceBuilder().type(BasicDataSource.class).build();
    }

    @Bean(name="orderEntityManagerFactory")
    LocalContainerEntityManagerFactoryBean getLocalEMF(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(getDatasource()).packages("com.example.sql.entity").build();
    }


    @Bean
    PlatformTransactionManager getOrderPTM(
            final @Qualifier("orderEntityManagerFactory") LocalContainerEntityManagerFactoryBean productEntityManagerFactoryBean) {
        return new JpaTransactionManager(productEntityManagerFactoryBean.getObject());
    }
}

