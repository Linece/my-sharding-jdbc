package com.zdc.sharding.java.config;

import io.shardingjdbc.core.api.config.ShardingRuleConfiguration;
import io.shardingjdbc.core.api.config.TableRuleConfiguration;
import io.shardingjdbc.core.api.config.strategy.StandardShardingStrategyConfiguration;
import io.shardingjdbc.core.jdbc.core.datasource.ShardingDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

	@Bean
	@Primary
	public DataSource dataSource1() throws SQLException {
		ShardingRuleConfiguration configuration =new ShardingRuleConfiguration();
		//默认的分库策略
		configuration.setDefaultDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("user_id",DBShardAlgo.class.getName() ));
		//默认分表策略
		configuration.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("user_id",TblPreShardAlgo.class.getName() ,TblRangeShardAlgo.class.getName()));
		// 为user_info表设置分库分表策略、算法
		configuration.getTableRuleConfigs().add(getUserTableRuleConfiguration());
		return new ShardingDataSource(configuration.build(createDataSourceMap()));

	}

	// 配置数据源
	private Map<String, DataSource> createDataSourceMap() {
		Map<String, DataSource> result = new HashMap<>();
		result.put("test0", createDataSource("jdbc:mysql://106.55.158.81:3306/test0?characterEncoding=utf8&useSSL=false&serverTimezone=UTC"));
		result.put("test1", createDataSource("jdbc:mysql://106.55.158.81:3306/test1?characterEncoding=utf8&useSSL=false&serverTimezone=UTC"));
		return result;
	}
	// 根据数据源地址创建 DataSource
	private DataSource createDataSource(final String dataSourceName) {
		BasicDataSource result = new BasicDataSource();
		result.setDriverClassName("com.mysql.jdbc.Driver");
		result.setUrl(dataSourceName);
		result.setUsername("root");
		result.setPassword("123456");
		return result;
	}

	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource){
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource);
		return dataSourceTransactionManager;
	}
//	// 为user_info表设置分库分表策略、算法
	public TableRuleConfiguration getUserTableRuleConfiguration() {
		TableRuleConfiguration userTableRuleConfig=new TableRuleConfiguration();
		userTableRuleConfig.setLogicTable("user_info");
		userTableRuleConfig.setActualDataNodes("test0.user_info, test1.user_info");
		userTableRuleConfig.setDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("user_id", DBShardAlgo.class.getName()));
		userTableRuleConfig.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("user_id",TblPreShardAlgo.class.getName(), TblRangeShardAlgo.class.getName()));
		return userTableRuleConfig;
	}
}
