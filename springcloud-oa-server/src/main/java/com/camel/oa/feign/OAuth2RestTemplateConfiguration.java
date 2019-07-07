package com.camel.oa.feign;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2RequestAuthenticator;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

@Configuration
public class OAuth2RestTemplateConfiguration {
    @Bean
    public OAuth2RestTemplate oauth2RestTemplate() {
        ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
        resourceDetails.setClientId("android");
        resourceDetails.setClientSecret("android");
        resourceDetails.setId("springcloud-oa-server");
        resourceDetails.setAccessTokenUri("http://127.0.0.1:8080/auth/oauth/token");
        OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(resourceDetails);
        oAuth2RestTemplate.setAuthenticator(new DefaultOAuth2RequestAuthenticator());
        return oAuth2RestTemplate;
    }
}
