package com.smartbee.jamu.jpos.server.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.*;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by galihlasahido on 10/17/14.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.smartbee.jamu.jpos.server.repository.sdata")
@PropertySource("classpath:hibernate.properties")
@ComponentScan(value = "com.smartbee.jamu.jpos.server")
public class SDatabase {

    @Resource
    private Environment env;

    @Bean
    public ComboPooledDataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(env.getProperty("db.driver"));
        comboPooledDataSource.setJdbcUrl(env.getProperty("db.url"));
        comboPooledDataSource.setUser(env.getProperty("db.username"));
        comboPooledDataSource.setPassword(env.getProperty("db.password"));

        return comboPooledDataSource;

    }


    @Bean
    public EntityManagerFactory entityManagerFactory() throws SQLException, PropertyVetoException {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(org.springframework.orm.jpa.vendor.Database.MYSQL);
        vendorAdapter.setShowSql(Boolean.valueOf(env.getProperty("db.hibernate.show_sql")));
        vendorAdapter.setGenerateDdl(Boolean.valueOf(env.getProperty("db.hibernate.hbm2ddl.auto")));

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setJpaProperties(hibernateProperties());
        factory.setPackagesToScan("com.smartbee.jamu.jpos.server.domain.sdata");
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager();
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws SQLException, PropertyVetoException {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }

    Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.hbm2ddl.auto", env.getProperty("db.hibernate.hbm2ddl.auto"));
                setProperty("hibernate.dialect", env.getProperty("db.hibernate.dialect"));
                setProperty("hibernate.globally_quoted_identifiers", env.getProperty("db.hibernate.globally_quoted_identifiers"));
                setProperty("hibernate.use_sql_comments", env.getProperty("db.hibernate.use_sql_comments"));
                setProperty("hibernate.show_sql", env.getProperty("db.hibernate.show_sql"));
                setProperty("hibernate.format_sql", env.getProperty("db.hibernate.format_sql"));
            }
        };
    }

//    @Bean
//    public SpringLiquibase liquibase() throws PropertyVetoException {
//        SpringLiquibase springLiquibase = new SpringLiquibase();
//        springLiquibase.setDataSource(dataSource());
//        springLiquibase.setChangeLog("classpath:db-changelog.xml");
//        System.out.println(">>>>>>"+springLiquibase.getChangeLog());
//        springLiquibase.setContexts("production");
//        return springLiquibase;
//    }
}
