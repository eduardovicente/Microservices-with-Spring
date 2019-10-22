package com.vicentemartinez.microservicecoursemanagement.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
public class HibernateConfig {
	@Autowired
	private Environment environment;
	
	@Bean
	public JpaVendorAdapter getJpaVendorAdapter() {
		return new HibernateJpaVendorAdapter();
	}
	
	@Bean
    public PlatformTransactionManager transactionManager(){
        return new JpaTransactionManager(entityManagerFactory().getObject());
    }
	
	@Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean icemfb = new LocalContainerEntityManagerFactoryBean();
        icemfb.setJpaVendorAdapter(getJpaVendorAdapter());
        icemfb.setDataSource(dataSource());
        icemfb.setPersistenceUnitName("entityManagerFactory");
        icemfb.setPackagesToScan("com.vicentemartinez.microservicecoursemanagement.model");
        icemfb.setJpaProperties(hibernateProperties());
        return icemfb;
    }
	
	@Bean
    public DataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(environment.getProperty("spring.datasource.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));
        return dataSource;
    }
	
	private final Properties hibernateProperties(){
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.max_fetch_depth", environment.getProperty("hibernate.max_fetch_depth"));
        properties.put("hibernate.cache.use_second_level_cache", environment.getProperty("hibernate.cache.use_second_level_cache"));
        properties.put("hibernate.cache.use_minimal_puts", environment.getProperty("hibernate.cache.use_minimal_puts"));
        properties.put("hibernate.connection_release_mode", environment.getProperty("hibernate.connection_release_mode"));
        properties.put("hibernate.cache.use_query_cache", environment.getProperty("hibernate.cache.use_query_cache"));
        return properties;
    }
}
