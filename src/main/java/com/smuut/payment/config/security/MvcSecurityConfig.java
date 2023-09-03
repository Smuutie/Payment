package com.smuut.payment.config.security;

import com.smuut.payment.config.KeycloakProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class MvcSecurityConfig {

  private final KeycloakProperties keycloakProperties;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    // Setup custom converter for keycloak as the roles are deep in side the jwt token
    final var jwtConverter = new JwtAuthenticationConverter();
    jwtConverter.setJwtGrantedAuthoritiesConverter(new KeycloakJwtConverter(keycloakProperties));

    // Require all requests to be authenticated
    return http.authorizeHttpRequests(
            authorizationManagerRequestMatcherRegistry ->
                authorizationManagerRequestMatcherRegistry.anyRequest().authenticated())
        // configure role extraction from keycloak jwt to allow use of @PreAuth and @RolesAllowed
        .oauth2ResourceServer(
            httpSecurityOAuth2ResourceServerConfigurer ->
                httpSecurityOAuth2ResourceServerConfigurer.jwt(
                    jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtConverter)))
        // Configure session to be stateless
        .sessionManagement(
            httpSecuritySessionManagementConfigurer ->
                httpSecuritySessionManagementConfigurer.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS))
        .build();
  }
}
