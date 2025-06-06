package com.united.coders.cmsuser.configuration.security.jwt;

import com.united.coders.cmsuser.adapter.driven.jpa.postgres.entity.PrincipalUser;
import com.united.coders.cmsuser.adapter.driving.http.dto.response.JwtResponseDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtProvider {
    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    // Genera una clave segura usando el secret
    private SecretKey getSecretKey() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(Authentication authentication) {
        PrincipalUser usuarioPrincipal = (PrincipalUser) authentication.getPrincipal();
        List<String> roles = usuarioPrincipal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return Jwts.builder()
                .subject(usuarioPrincipal.getUsername())
                .claim("roles", roles)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration * 180))
                .signWith(getSecretKey())
                .compact();
    }

    public String getNombreUsuarioFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("token mal formado");
        } catch (UnsupportedJwtException e) {
            logger.error("token no soportado");
        } catch (ExpiredJwtException e) {
            logger.error("token expirado");
        } catch (IllegalArgumentException e) {
            logger.error("token vacío");
        } catch (SecurityException e) {
            logger.error("fail en la firma");
        }
        return false;
    }

    public String refreshToken(JwtResponseDto jwtResponseDto) {
        try {
            Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(jwtResponseDto.getToken());
        } catch (ExpiredJwtException e) {
            Claims claims = e.getClaims();
            String nombreUsuario = claims.getSubject();
            List<String> roles = claims.get("roles", List.class);

            return Jwts.builder()
                    .subject(nombreUsuario)
                    .claim("roles", roles)
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis() + expiration))
                    .signWith(getSecretKey())
                    .compact();
        } catch (JwtException | IllegalArgumentException e) {
            logger.error("Error al refrescar token: {}", e.getMessage());
        }
        return null;
    }
}