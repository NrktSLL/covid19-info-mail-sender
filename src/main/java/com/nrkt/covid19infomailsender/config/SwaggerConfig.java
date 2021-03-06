package com.nrkt.covid19infomailsender.config;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SwaggerConfig {

    @Bean
    public Docket apiV1() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.nrkt.covid19infomailsender.controller.rest"))
                .paths(PathSelectors.regex("/api.*"))
                .build()
                .apiInfo(apiEndpointInfo())
                .tags(new Tag("Contact", "Contact Information"))
                .useDefaultResponseMessages(false);
    }

    private ApiInfo apiEndpointInfo() {
        return new ApiInfoBuilder()
                .title("Covid19 Case Information Service Mail Sender")
                .description("Covid19 Case Information Service Mail Sender")
                .contact(new Contact("Nrkt", "", ""))
                .version("1.0.0")
                .build();
    }
}
