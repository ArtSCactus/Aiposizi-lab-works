package com.bsuir.jwt;

import com.bsuir.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Date;

@Component
public class JwtTokenUtil implements Serializable {

    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 2*60*60;
    @Value("${security.jwt.token.secret-key:secret}")
    private static String SIGNING_KEY = "c2VjcmV0";

    public static String generateToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getEmail());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer("http://localhost:8081")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS*1000))
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .compact();
    }

}