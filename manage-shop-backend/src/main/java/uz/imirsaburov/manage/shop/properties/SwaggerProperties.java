package uz.imirsaburov.manage.shop.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Getter
@Setter
@Configuration
@ConfigurationProperties("custom.swagger")
public class SwaggerProperties {
    private String url;
    private String basePackage;
    private String tokenUrl;
    private String oauth2ClientUsername;
    private String oauth2ClientPassword;
}
