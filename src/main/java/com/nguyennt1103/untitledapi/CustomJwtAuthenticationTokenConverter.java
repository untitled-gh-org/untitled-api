package com.nguyennt1103.untitledapi;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;

@RequiredArgsConstructor
public class CustomJwtAuthenticationTokenConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final AuthoritiesConverter authoritiesConverter;

    @Nullable
    @Override
    public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
        Collection<GrantedAuthority> authorities = authoritiesConverter.convert(jwt.getClaims());

        return new JwtAuthenticationToken(jwt, authorities, jwt.getSubject());
    }
}
