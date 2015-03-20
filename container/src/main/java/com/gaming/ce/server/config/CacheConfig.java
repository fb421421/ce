package com.gaming.ce.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@EnableCaching
public class CacheConfig {

	@Autowired
	private Environment env;

	@Bean
	public CacheManager cacheManager() {

		CacheManager cacheManager = new ConcurrentMapCacheManager("session","roles","checkCode");

		return cacheManager;
	}

}
