package com.gaming.ce.server.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import com.jolbox.bonecp.BoneCPDataSource;

@Configuration
public class RepositoryConfig {

	@Autowired
	Environment env;
	
	@Bean
	public JdbcTemplate jdbcTemplate(){
		return new JdbcTemplate(dataSource());
	}

	@Bean
	public DataSource dataSource() {

		BoneCPDataSource boneCPDataSource = new BoneCPDataSource();
		boneCPDataSource.setDriverClass(env.getProperty("spring.datasource.driverClassName"));
		boneCPDataSource.setJdbcUrl(env.getProperty("spring.datasource.url"));
		boneCPDataSource.setUsername(env.getProperty("spring.datasource.username"));
		boneCPDataSource.setPassword(env.getProperty("spring.datasource.password"));
		boneCPDataSource.setIdleConnectionTestPeriodInMinutes(60);
		boneCPDataSource.setIdleMaxAgeInMinutes(420);
		boneCPDataSource.setMaxConnectionsPerPartition(env.getProperty("cp.connection.max",Integer.class));
		boneCPDataSource.setMinConnectionsPerPartition(10);
		boneCPDataSource.setPartitionCount(3);
		boneCPDataSource.setAcquireIncrement(5);
		boneCPDataSource.setStatementsCacheSize(100);
		boneCPDataSource.setDisableConnectionTracking(true);
		
		return boneCPDataSource;

	}

}
