package uz.imirsaburov.manage.shop.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import uz.imirsaburov.manage.shop.properties.oauth2.OAuth2ClientsProperties;
import uz.imirsaburov.manage.shop.service.CustomUserDetailsService;

@EnableAuthorizationServer
@Configuration
@RequiredArgsConstructor
public class AuthorizationConfiguration extends AuthorizationServerConfigurerAdapter {

    private final OAuth2ClientsProperties oAuth2ClientsProperties;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenStore tokenStore;
    private final CustomUserDetailsService userDetailsService;
    private final DefaultTokenServices defaultTokenServices;


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                .passwordEncoder(passwordEncoder)
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        InMemoryClientDetailsServiceBuilder inMemory = clients.inMemory();

        for (OAuth2ClientsProperties.OAuth2ClientProperty oAuth2ClientProperty : oAuth2ClientsProperties.getClientList()) {
            inMemory
                    .withClient(oAuth2ClientProperty.getUsername())
                    .secret(passwordEncoder.encode(oAuth2ClientProperty.getPassword()))
                    .accessTokenValiditySeconds(oAuth2ClientProperty.getAccessTokenValidSeconds())
                    .refreshTokenValiditySeconds(oAuth2ClientProperty.getRefreshTokenValidSeconds())
                    .authorizedGrantTypes(oAuth2ClientProperty.getGrantTypes())
                    .resourceIds(oAuth2ClientProperty.getResourceIds())
                    .scopes(oAuth2ClientProperty.getScopes())
                    .and();
        }
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .reuseRefreshTokens(false)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
//                .tokenServices(defaultTokenServices)
                .tokenStore(tokenStore);
    }
}
