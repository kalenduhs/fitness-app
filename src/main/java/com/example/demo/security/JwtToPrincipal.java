package com.example.demo.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtToPrincipal {

    public UserPrincipal convert(DecodedJWT jwt){
        return new UserPrincipal(Long.parseLong(jwt.getSubject()), jwt.getClaim("e").asString(), extractAuthoritiesFromClaim(jwt));
    }

    private List<SimpleGrantedAuthority> extractAuthoritiesFromClaim(DecodedJWT jwt){
        var claim = jwt.getClaim("a");
        if (claim.isNull() || claim.isMissing()) {
            return List.of();
        }
        return claim.asList(SimpleGrantedAuthority.class);
    }
}
