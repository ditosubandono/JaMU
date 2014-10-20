package com.smartbee.jamu.jpos.server.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import java.beans.PropertyVetoException;

/**
 * Created by galihlasahido on 10/17/14.
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
@ComponentScan(value = "com.smartbee.jamu.jpos.server")
public class Database {
    @Resource
    private Environment env;

    @Bean
    public ComboPooledDataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(env.getProperty("jdbc.driver"));
        comboPooledDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
        comboPooledDataSource.setUser(env.getProperty("jdbc.username"));
        comboPooledDataSource.setPassword(env.getProperty("jdbc.password"));

        return comboPooledDataSource;

    }

    @Bean
    public DataSourceTransactionManager transactionManager() throws PropertyVetoException {
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(dataSource());
        return manager;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws PropertyVetoException {
        JdbcTemplate manager = new JdbcTemplate();
        manager.setDataSource(dataSource());
        return manager;
    }
}
