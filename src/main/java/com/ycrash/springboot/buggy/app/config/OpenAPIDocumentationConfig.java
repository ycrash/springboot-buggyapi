/*************************************************************************
* 
* FUTURE BUSINESS VISION LTD - PROPERIETRY AND CONFIDENTIAL
* _________________________________________________________
* 
* Copyright (C) 2022 - Future Business Vision Ltd - All Rights Reserved
* Unauthorised copying of this file, via any medium is strictly prohibited
* Proprietary and confidential
* 
* NOTICE:  All information contained herein is, and remains
* the property of Future Business Vision Ltd Incorporated and its suppliers,
* if any.  The intellectual and technical concepts contained
* herein are proprietary to Future Business Vision Ltd Incorporated
* and its suppliers and may be covered by U.K. and Foreign Patents,
* patents in process, and are protected by trade secret or copyright law.
* Dissemination of this information or reproduction of this material
* is strictly forbidden unless prior written permission is obtained
* from Future Business Vision Ltd Incorporated.
* 
* Written and managed by Future Business Vision Ltd <legal@moneysuite.co.uk>, 
*/
package com.ycrash.springboot.buggy.app.config;

import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriComponentsBuilder;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.Paths;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class OpenAPIDocumentationConfig {
	
	ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Tier1App - SpringBoot Buggy API Service")
				.description("Documentation for SpringBoot Buggy Api Service")
				.termsOfServiceUrl("")
				.version("1.0.0")
				.build();				
	}
	

    class BasePathAwareRelativePathProvider extends RelativePathProvider {
        private String basePath;

        public BasePathAwareRelativePathProvider(ServletContext servletContext, String basePath) {
            super(servletContext);
            this.basePath = basePath;
        }

        @Override
        protected String applicationPath() {
            return  Paths.removeAdjacentForwardSlashes(UriComponentsBuilder.fromPath(super.applicationPath()).path(basePath).build().toString());
        }

        @Override
        public String getOperationPath(String operationPath) {
            UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath("/");
            return Paths.removeAdjacentForwardSlashes(
                    uriComponentsBuilder.path(operationPath.replaceFirst("^" + basePath, "")).build().toString());
        }
    }
    
	@Bean
    public Docket productImplementation(ServletContext servletContext, @Value("") String basePath) {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                    .apis(RequestHandlerSelectors.basePackage("com.ycrash.springboot.buggy.app.controller"))
                    .build()
                .pathProvider(new BasePathAwareRelativePathProvider(servletContext, basePath))
                .directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(java.time.OffsetDateTime.class, java.util.Date.class)
                .genericModelSubstitutes(Optional.class)
                .apiInfo(apiInfo());
    }

}
