package com.feisystems.provider.web.config.di;

import java.io.IOException;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.jasypt.encryption.pbe.config.PBEConfig;
import org.jasypt.spring31.properties.EncryptablePropertyPlaceholderConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.Assert;

@Configuration
public class PropertyPlaceholderConfig {

	private static final Logger logger = LoggerFactory.getLogger(PropertyPlaceholderConfig.class);

	private static final String propertyFiles = "/pls-api-web-config.properties";

	/**
	 * Use static method. Otherwise will get the following warning:
	 *
	 * <p>o.s.c.a.ConfigurationClassEnhancer - @Bean method PropertyPlaceholderConfig.propertyPlaceholderConfigurer is non-static and returns an object assignable to Spring's BeanFactoryPostProcessor interface.
	 * This will result in a failure to process annotations such as @Autowired, @Resource and @PostConstruct within the method's declaring @Configuration class.
	 * Add the 'static' modifier to this method to avoid these container lifecycle issues; see @Bean javadoc for complete details
	 *
	 * @return
	 * @throws IOException
	 */
	@Bean
	public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer(Environment env)
			throws IOException {
		Assert.notNull(env);

		EncryptablePropertyPlaceholderConfigurer configurer = new EncryptablePropertyPlaceholderConfigurer(
				stringEncryptor());
		configurer.setIgnoreUnresolvablePlaceholders(true);
		configurer
				.setSystemPropertiesModeName("SYSTEM_PROPERTIES_MODE_OVERRIDE");

		configurer.setSearchSystemEnvironment(false);

		// Use Environment.getProperty instead of System.getProperty to get more flexibility to locate the property
		final String configBasePath = env.getProperty("C2S_PROPS");

		logger.debug("C2S_PROPS property value: " + configBasePath);

		configurer.setLocations(new PathMatchingResourcePatternResolver()
				.getResources("file:" + configBasePath + propertyFiles));
		return configurer;
	}

	@Bean
	public static PBEConfig pbeConfig() {
		EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
		config.setAlgorithm("PBEWithMD5AndDES");
		config.setPasswordSysPropertyName("C2S_KEY");
		return config;
	}

	@Bean
	public static StringEncryptor stringEncryptor() {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setConfig(pbeConfig());
		return encryptor;
	}

}
