package xyz.inhalt.springcloak.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.inhalt.springcloak.auth.JwtAuthProvider;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AppController {

    private final JwtAuthProvider jwtAuthProvider;

    @GetMapping
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello!");
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> helloUser(@AuthenticationPrincipal Jwt jwt) {
        // Get the username from the JWT
        String username = jwt.getClaimAsString("preferred_username");
        return ResponseEntity.ok("Hello my friend" + " "  + username);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> helloAdmin() {
        return ResponseEntity.ok("Hello my friend" + " " + jwtAuthProvider.getUsername());
    }
}