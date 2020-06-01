package com.bsuir.jwt;

import com.bsuir.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Date;

@Component
public class JwtTokenUtil implements Serializable {

    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 1*60*60;
    private static String SIGNING_KEY= "root";

    public static String generateToken(User user) {
        Claims claims = Jwts.claims().setSubject(String.valueOf(user.getId()));

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer("http://university-rest-server.herokuapp.com")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS*1000))
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .compact();
    }

}