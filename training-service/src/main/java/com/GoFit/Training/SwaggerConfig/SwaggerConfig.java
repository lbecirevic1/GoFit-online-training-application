package com.GoFit.Training.SwaggerConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {

        @Bean
        public Docket api() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .securityContexts(Collections.singletonList(securityContext()))
                    .securitySchemes(Collections.singletonList(apiKey()))
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.GoFit.Training"))
                    .paths(PathSelectors.any())
                    .build();
        }


        private ApiInfo apiInfo() {
            return new ApiInfoBuilder().title("GoFit")
                    .description("API documentation")
                    .license("Apache 2.0")
                    .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0.html")
                    .version("1.0.0")
                    .build();
        }

        private SecurityContext securityContext() {
            return SecurityContext.builder().securityReferences(defaultAuth()).build();
        }

        private List<SecurityReference> defaultAuth() {
            AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
            AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
            authorizationScopes[0] = authorizationScope;
            return Collections.singletonList(new SecurityReference("JWT", authorizationScopes));
        }

        private ApiKey apiKey() {
            return new ApiKey("JWT", "Authorization", "header");
        }

}
