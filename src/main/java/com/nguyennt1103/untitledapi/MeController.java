package com.nguyennt1103.untitledapi;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RestController
public class MeController {

    @GetMapping("/me")
    public UserInfoDto getMe(Authentication auth) {
        if (!(auth instanceof JwtAuthenticationToken jwtAuth)) {
            return UserInfoDto.ANONYMOUS;
        }

        final Jwt jwt = jwtAuth.getToken();

        final String email = jwt.getClaimAsString(StandardClaimNames.EMAIL);

        final List<String> roles = jwtAuth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        final Long exp = Optional.ofNullable(jwt.getExpiresAt())
                .map(Instant::getEpochSecond)
                .orElse(Long.MAX_VALUE);

        return new UserInfoDto(auth.getName(), email, roles, exp);
    }
}
