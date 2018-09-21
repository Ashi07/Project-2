package com.niit.Configuration;


import java.util.Properties;


import javax.sql.DataSource;


import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement

public class DBConfig {

	 @Bean(name="dataSource")
     public DataSource getH2DataSource()
     {
    	 DriverManagerDataSource dataSource = new DriverManagerDataSource();
    	 dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
    	 dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
    	 dataSource.setUsername("system");
    	 dataSource.setPassword("12345");
    	 
    	 System.out.println("data source object created");
    	 return dataSource;
     }

@Bean(name ="sessionFactory")
public SessionFactory getSessionFactory()
{
	Properties properties = new Properties();
	properties.put("hibernate.hbm2ddl.auto", "update");
	properties.put("hibernate.show_sql", "true");
	properties.put("hibernate.format_sql", "true");
	properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
    LocalSessionFactoryBuilder sessionFactoryBuilder= new LocalSessionFactoryBuilder(getH2DataSource());
      sessionFactoryBuilder.addProperties(properties);
      sessionFactoryBuilder.scanPackages("com.niit");
      SessionFactory sessionFactory = sessionFactoryBuilder.buildSessionFactory();
      System.out.println("sessionfactory object created");
      return sessionFactory;
}
        @Bean
        public HibernateTransactionManager getHibernateTransaction(SessionFactory sessionFactory) {
        	System.out.println("hibernate transaction object created");
            return new HibernateTransactionManager(sessionFactory);

		}
} 
