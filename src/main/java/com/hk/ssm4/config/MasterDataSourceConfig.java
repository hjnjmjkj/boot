package com.hk.ssm4.config;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.apache.ibatis.plugin.Interceptor;
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
import org.springframework.transaction.jta.JtaTransactionManager;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.github.pagehelper.PageHelper;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

@Configuration
@MapperScan(basePackages = {"com.hk.ssm4.mapper.master"}, sqlSessionTemplateRef = "sqlSessionTemplate")
public class MasterDataSourceConfig {
	
	@Autowired
	Environment env;
	
	/**
	 * 主数据源
	 */
	@Primary
    @Bean(name="dataSource")
    public AtomikosDataSourceBean primaryDataSource(MysqlXaConfig mysqlXaConfig) throws SQLException{
		MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
		//master配置url
		mysqlXaDataSource.setUrl(env.getProperty("spring.jta.atomikos.datasource.master.xa-properties.url"));
		//master配置用户
		mysqlXaDataSource.setUser(env.getProperty("spring.jta.atomikos.datasource.master.xa-properties.user"));
		//master密码
		mysqlXaDataSource.setPassword(env.getProperty("spring.jta.atomikos.datasource.master.xa-properties.password"));
		mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
		AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
		xaDataSource.setXaDataSource(mysqlXaDataSource);
        //主数据源唯一名称
		xaDataSource.setUniqueResourceName(env.getProperty("spring.jta.atomikos.datasource.master.unique-resource-name"));//
		//主数据源类名称
		xaDataSource.setXaDataSourceClassName(env.getProperty("spring.jta.atomikos.datasource.master.xa-data-source-class-name"));
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
     * 配置SqlSessionFactoryBean
     */
	@Bean(name = "sqlSessionFactory")
	@ConditionalOnMissingBean // 当容器里没有指定的Bean的情况下创建该对象
	@Primary
	public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis/mybatis-config.xml"));
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/master/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}
	/**
	 * 配置sqlSessionTemplate
	 */
	@Bean(name = "sqlSessionTemplate")
	@Primary
	public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory){
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
    
}
