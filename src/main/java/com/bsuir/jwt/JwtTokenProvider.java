package com.bsuir.jwt;

import com.bsuir.exception.InvalidJwtAuthenticationException;
import com.bsuir.user.Authority;
import com.bsuir.user.User;
import com.bsuir.user.CustomUserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.bind.DatatypeConverter;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private static Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);
    private String secretKey = "root";

    @Autowired
    private CustomUserRepository repository;

    public String createToken(User user) {
        return JwtTokenUtil.generateToken(user);
    }

    /**Extracts user details from given token according to database.
     *
     * If there's no such user with given credentials, will be returned
     * a null value.
     *
     * @param token
     * @return
     */
    public Authentication getAuthentication(String token) throws InvalidJwtAuthenticationException {
            UserDetails userDetails = repository.findUserById(getId(token));
            if (userDetails == null) {
                throw new InvalidJwtAuthenticationException("No such user. Access denied.");
            }
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public Long getId(String token) throws InvalidJwtAuthenticationException {
        Jws<Claims> claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey)).parseClaimsJws(token);
        try {
            Long id = Long.valueOf(claims.getBody().getSubject());
            LOGGER.info("Id from token: "+id);
            return id;
        } catch (NumberFormatException e){
            throw new InvalidJwtAuthenticationException("Invalid token format. Id is not present in subject.");
        }

    }

    public boolean validateToken(String token) throws InvalidJwtAuthenticationException {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey)).parseClaimsJws(token);
            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidJwtAuthenticationException(e.getMessage());
        }
    }

}