package com.hardik.dory.security.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "com.hardik.dory")
@Data
public class JwtConfiguration {

	private Configuration jwt = new Configuration();

	@Data
	public class Configuration {
		private String secretKey;
	}

}
