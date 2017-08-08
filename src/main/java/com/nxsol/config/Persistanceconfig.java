package com.nxsol.config;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource(value="classpath:com/nxsol/resource/config.properties")
public class Persistanceconfig {

	//Database 
	@Value("${DriverClassName}")
	private String DriverClassName;

	@Value("${DatabaseUrl}")
	private String DatabaseUrl;
	
	@Value("${uname}")
	private String uname;
	
	@Value("${pwd}")
	private String pwd;
	
	//Hibernate
	@Value("${HibernateDialect}")
	private String HibernateDialect;
	
	@Value("${HibernateShowSql}")
	private String HibernateShowSql;
	
	@Value("${HibernateHbm2ddlAuto}")
	private String HibernateHbm2ddlAuto;
	
	@Bean(name="dataSource")
	public DriverManagerDataSource dataSource()
	{
		System.out.println("username===="+uname);
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(DriverClassName);
		dataSource.setUrl(DatabaseUrl);
		dataSource.setUsername(uname);
		dataSource.setPassword(pwd);
		return dataSource;
	}
	
	@Bean(name="sessionFactory")
	public SessionFactory sessionFactory()
	{
		LocalSessionFactoryBuilder builder=new LocalSessionFactoryBuilder(dataSource());
		builder.scanPackages("com.nxsol");
		builder.addProperties(hibernateProperties());
		return builder.buildSessionFactory();
	}
	
	private Properties hibernateProperties()
	{
		Properties prop = new Properties();
		prop.put("hibernate.dialect",HibernateDialect);
        prop.put("hibernate.show_sql",HibernateShowSql);
        prop.put("hibernate.hbm2ddl.auto",HibernateHbm2ddlAuto);
        return prop;
	}
	
	@Bean(name="transactionManager")
    public HibernateTransactionManager transactionManager() {
            return new HibernateTransactionManager(sessionFactory());
    }
	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer()
	{
		return new PropertySourcesPlaceholderConfigurer();
	}
}
