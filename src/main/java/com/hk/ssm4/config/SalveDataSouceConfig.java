package com.hk.ssm4.config;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.github.pagehelper.PageHelper;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

@Configuration
@MapperScan(basePackages = {"com.hk.ssm4.mapper.salve"}, sqlSessionTemplateRef = "salveSqlSessionTemplate")
public class SalveDataSouceConfig {
	@Autowired
	Environment env;
	
	/**
     * 配置salveSqlSessionFactory
     */
	@Bean(name = "salveSqlSessionFactory")
	public SqlSessionFactory salveSqlSessionFactory(@Qualifier("salveDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis/mybatis-config.xml"));
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/salve/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}
	/**
	 * 副数据源配置sqlSessionTemplate
	 */
	@Bean(name = "salveSqlSessionTemplate")
	public SqlSessionTemplate salveSqlSessionTemplate(@Qualifier("salveSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
	/**********************配置salve副数据源**************************/
	/**
	 * 副数据源
	 */
    @Bean(name="salveDataSource")
    public AtomikosDataSourceBean salveDataSource(MysqlXaConfig mysqlXaConfig) throws SQLException{
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
 		//master配置url
 		mysqlXaDataSource.setUrl(env.getProperty("spring.jta.atomikos.datasource.salve.xa-properties.url"));
 		//master配置用户
 		mysqlXaDataSource.setUser(env.getProperty("spring.jta.atomikos.datasource.salve.xa-properties.user"));
 		//master密码
 		mysqlXaDataSource.setPassword(env.getProperty("spring.jta.atomikos.datasource.salve.xa-properties.password"));
 		mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
 		AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
 		xaDataSource.setXaDataSource(mysqlXaDataSource);
         //主数据源唯一名称
 		xaDataSource.setUniqueResourceName(env.getProperty("spring.jta.atomikos.datasource.salve.unique-resource-name"));//
 		//主数据源类名称
 		xaDataSource.setXaDataSourceClassName(env.getProperty("spring.jta.atomikos.datasource.salve.xa-data-source-class-name"));
 		xaDataSource.setMinPoolSize(mysqlXaConfig.getMinPoolSize());
 		xaDataSource.setMaxPoolSize(mysqlXaConfig.getMaxPoolSize());
 		xaDataSource.setMaxLifetime(mysqlXaConfig.getMaxLifetime());
 		xaDataSource.setBorrowConnectionTimeout(mysqlXaConfig.getBorrowConnectionTimeout());
 		xaDataSource.setLoginTimeout(mysqlXaConfig.getLoginTimeout());
 		xaDataSource.setMaintenanceInterval(mysqlXaConfig.getMaintenanceInterval());
 		xaDataSource.setMaxIdleTime(mysqlXaConfig.getMaxIdleTime());
 		xaDataSource.setTestQuery(mysqlXaConfig.getTestQuery());  
 		return xaDataSource; 
    }
	
	
	/**
	 * 配置pageHelper插件
	 */
    @Bean(name="salvePageHelper")
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable","true");
        properties.setProperty("dialect","mysql");    //配置mysql数据库的方言
        properties.setProperty("offsetAsPageNum","true");
        properties.setProperty("rowBoundsWithCount","true");
        properties.setProperty("pageSizeZero","true");
        properties.setProperty("params","pageNum=start;pageSize=limit;pageSizeZero=zero;reasonable=heli;count=contsql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
