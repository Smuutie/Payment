package com.smuut.payment.config.security;

import com.smuut.payment.config.KeycloakProperties;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

@RequiredArgsConstructor
public class KeycloakJwtConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

  private static final String RESOURCE_ACCESS_CLAIM = "resource_access";
  private static final String ROLE_ACCESS_CLAIM = "roles";

  private final KeycloakProperties keycloakProperties;

  @Override
  @SuppressWarnings("unchecked")
  public Collection<GrantedAuthority> convert(Jwt jwt) {
    if (!jwt.hasClaim(RESOURCE_ACCESS_CLAIM)) {
      return new ArrayList<>();
    }
    final var resourceClaims = jwt.getClaimAsMap(RESOURCE_ACCESS_CLAIM);
    if (!resourceClaims.containsKey(keycloakProperties.getClient())) {
      return new ArrayList<>();
    }
    final var resourceData =
        (Map<String, Object>) resourceClaims.get(keycloakProperties.getClient());
    if (!resourceData.containsKey(ROLE_ACCESS_CLAIM)) {
      return new ArrayList<>();
    }
    return ((Collection<String>) resourceData.get(ROLE_ACCESS_CLAIM))
        .stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
  }
}
