package uz.imirsaburov.manage.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import uz.imirsaburov.manage.shop.properties.SwaggerProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {


    private final SwaggerProperties swaggerProperties;

    public SwaggerConfig(SwaggerProperties swaggerProperties) {
        this.swaggerProperties = swaggerProperties;
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .host(swaggerProperties.getUrl())
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Collections.singletonList(apiKey()))
                .securityContexts(Collections.singletonList(securityContext())).pathMapping("/")
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo());
    }

    private OAuth apiKey() {

        return new OAuth(
                "oauth2schema",
                new ArrayList<>(),
                Arrays.asList(new ResourceOwnerPasswordCredentialsGrant(swaggerProperties.getTokenUrl()))
        );
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Manage Shop",
                "",
                "0.0.1",
                "",
                new Contact("Islom  Mirsaburov", "https://www.linkedin.com/in/imirsaburov", "imirsaburov@gmail.com"),
                "",
                "",
                Collections.emptyList());
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[2];
        authorizationScopes[0] = new AuthorizationScope("write", "write all");
        authorizationScopes[1] = new AuthorizationScope("read", "read all");
        return Arrays.asList(new SecurityReference("oauth2schema", authorizationScopes));
    }

    @Bean
    public SecurityConfiguration securityInfo() {
        return new SecurityConfiguration(swaggerProperties.getOauth2ClientUsername(), swaggerProperties.getOauth2ClientPassword(), "", "", "", ApiKeyVehicle.HEADER, "", " ");
    }


}
