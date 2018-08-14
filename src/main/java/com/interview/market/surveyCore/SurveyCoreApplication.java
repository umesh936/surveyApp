package com.interview.market.surveyCore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.interview.market.surveyCore.cache.CacheImpl;

@SpringBootApplication
// @EnableCaching
public class SurveyCoreApplication {
	public static void main(String[] args) {
		SpringApplication.run(SurveyCoreApplication.class, args);
	}

	/**
	 * we can provide any cache implementation here like memcached , redis ,
	 * hazlecast etc
	 * 
	 * @return
	 */
	@Bean
	CacheImpl getQestionCacheImpl() {
		return new CacheImpl(100);
	}

	@Bean
	Map<Long, List<Long>> getSurveyCacheImpl() {
		return new HashMap<>();
	}
}
