package org.junit.test.dao.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"org.junit.test.dao", "org.junit.test.model"})
@PropertySource("classpath:test/config.properties")
public class JpaConfigurationTest {

	@Autowired
	Environment env;
	
	@Bean (destroyMethod = "close")
	public DataSource dataSource(){
		
		HikariConfig dataSourceConfig = new HikariConfig();
        dataSourceConfig.setDriverClassName(env.getRequiredProperty("org.h2.Drive"));
        dataSourceConfig.setJdbcUrl(env.getProperty("databaseUrl"));
        dataSourceConfig.setUsername(env.getProperty("user"));
        dataSourceConfig.setPassword(env.getProperty("password"));
 
        return new HikariDataSource(dataSourceConfig);
	}
	
	@Bean
	public Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		properties.setProperty("spring.jpa.generate-ddl", "true");
		properties.setProperty("hibernate.show_sql", "true");
	    return properties;
	}
	
	@Bean(name="entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] {"org.junit.test.model"});
		
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());
		System.out.println("entityManagerFactory");
		return em;
	}
	
	@Bean(name="transactionManager")
	public PlatformTransactionManager transactionalManager(EntityManagerFactory emf){
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);;
		
		return transactionManager;
	}
	
	@Bean 
	public PersistenceExceptionTranslationPostProcessor exceptionTraslation(){
		return new PersistenceExceptionTranslationPostProcessor();
	}
}
