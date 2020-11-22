/**
 * 
 */
package com.bank.account.conf;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * This class used to declare the common configuration for the Api.
 * 
 * @author Rached
 *
 */
@Configuration
@PropertySource("classpath:errors.properties")
public class ApiConfig {

	@Value(value = "${api.name}")
	private String apiName;
	
	@Value(value = "${api.version}")
	private String apiVersion;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.bank.account.controller"))
				.paths(PathSelectors.ant("/mybank/v1/operations")).build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(apiName, "This REST Api simplify your account management", apiVersion,
				"Terms of service", new Contact("Bank Admin", "www.mybank.com", "admin@mybank.com"), "License of API",
				"http://www.apache.org/licenses/LICENSE-2.0.html", Collections.emptyList());
	}

}
