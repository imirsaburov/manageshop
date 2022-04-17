package uz.imirsaburov.manage.shop.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("custom.admin")
public class AdminProperties {
    private String username;
    private String password;
}
