package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;

@Configuration
@EnableOpenApi
public class SwaggerConfig {
    public static final Contact DEFAULT_CONTACT = new Contact(
            "zefeng, zhang","http://www.zefeng.com","zefeng.zhang@test.com"
    );
    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
            "Demo API Title","Demo API Description","1.0","urn:tos", DEFAULT_CONTACT,"Apache 2.0","http://www.apache.org/licenses/LICENSE-2.0", Arrays.asList()
    );

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(DEFAULT_API_INFO)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

}
