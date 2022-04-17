package uz.imirsaburov.manage.shop.properties.oauth2;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Getter
@Setter
@Configuration
@ConfigurationProperties("custom.oauth2.client")
public class OAuth2ClientsProperties {
    private ArrayList<OAuth2ClientProperty> clientList;

    @Getter
    @Setter
    public static class OAuth2ClientProperty {
        private String username;
        private String password;
        private Integer accessTokenValidSeconds;
        private Integer refreshTokenValidSeconds;
        private String[] grantTypes;
        private String[] scopes;
        private String[] resourceIds;
    }
}
