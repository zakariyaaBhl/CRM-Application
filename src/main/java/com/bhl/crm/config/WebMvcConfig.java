package com.bhl.crm.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mchange.v2.c3p0.ComboPooledDataSource;


@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("com.bhl.crm")
@PropertySource("classpath:persistence-mysql.properties")
public class WebMvcConfig implements WebMvcConfigurer{

	@Autowired
	private Environment env;
	
	@Bean
	public DataSource dataSourceForProduct() {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		try {
			dataSource.setDriverClass(env.getProperty("jdbc.driver"));
			dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
			dataSource.setUser(env.getProperty("jdbc.user"));
			dataSource.setPassword(env.getProperty("jdbc.password"));
			
			dataSource.setInitialPoolSize(Integer.parseInt(env.getProperty("connection.pool.initialPoolSize")));
			dataSource.setMinPoolSize(Integer.parseInt(env.getProperty("connection.pool.minPoolSize")));
			dataSource.setMaxPoolSize(Integer.parseInt(env.getProperty("connection.pool.maxPoolSize")));
			dataSource.setMaxIdleTime(Integer.parseInt(env.getProperty("connection.pool.maxIdleTime")));

		} catch (PropertyVetoException e) {
			new RuntimeException(e);
		}
		return dataSource;
	}
	
	
	/*-- The next method handles the Hibernate properties --*/
	private Properties getHibernateProperties() {
		/*-- set hibernate properties --*/
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		properties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		return properties;
	}
	
	/*-- 
	 * The next method creates the Hibernate session factory based on the datasource and configuration properties
	 * Setup Hibernate session factory : session factory is what hibernate uses to talk to our DB 
	 * --*/
	/*-- Session factory for Products --*/
	@Bean
	public LocalSessionFactoryBean SessionFactoryForProduct() {
			LocalSessionFactoryBean factory= new LocalSessionFactoryBean();
			factory.setDataSource(dataSourceForProduct());
			factory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
			factory.setHibernateProperties(getHibernateProperties());
			return factory;
	}
	
	/*-- The next method configures the Hibernate transaction manager --*/
	
	@Bean
	public HibernateTransactionManager transactionManagerForProduct() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(SessionFactoryForProduct().getObject());
		return transactionManager;
	}










}
