package xyz.inhalt.springcloak.auth;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JwtAuthProvider {

    public String getUsername() {
        return getJwt().map(jwt -> jwt.getClaimAsString("preferred_username")).orElse("ANONYMOUS");
    }

    private Optional<Jwt> getJwt() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof JwtAuthenticationToken jwtAuth) {
            return Optional.of(jwtAuth.getToken());
        }
        return Optional.empty();
    }
}
