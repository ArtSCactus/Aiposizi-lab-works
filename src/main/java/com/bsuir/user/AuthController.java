package com.bsuir.user;

import com.bsuir.exception.InvalidJwtAuthenticationException;
import com.bsuir.jwt.JwtTokenProvider;
import com.bsuir.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private CustomUserRepository repository;

    @GetMapping("/oauth-2-endpoint")
    public void redirectToApplication(HttpServletRequest request, HttpServletResponse response) throws IOException {
       String name= request.getHeader("Username");
       User user = repository.findByName(name);
        String token = JwtTokenUtil.generateToken(user);
        Cookie cookie = new Cookie("access-token", token);
        cookie.setMaxAge(60 * 60);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setDomain("university-rest-server.herokuapp.com");
        response.addCookie(cookie);
        response.sendRedirect("https://university-view.herokuapp.com");

    }

    @PostMapping("/refresh-token")
    public ResponseEntity<HttpStatus> getToken(HttpServletRequest request, HttpServletResponse response) {
        try {
            Cookie oldTokenCookie = WebUtils.getCookie(request, "access-token");
            JwtTokenProvider tokenProvider = new JwtTokenProvider();
            if (oldTokenCookie != null) {
                if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                        & tokenProvider.validateToken(oldTokenCookie.getValue())) {
                    User user = extractUser();
                    String token = JwtTokenUtil.generateToken(user);
                    Cookie cookie = new Cookie("access-token", token);
                    cookie.setMaxAge(60 * 60);
                    cookie.setHttpOnly(true);
                    cookie.setPath("/");
                    cookie.setDomain("university-rest-server.herokuapp.com");
                    response.addCookie(cookie);
                    return ResponseEntity.status(200).build();
                } else {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (InvalidJwtAuthenticationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/token")
    public ResponseEntity<HttpStatus> formToken(HttpServletResponse response, @RequestParam String username, @RequestParam String password) {
        User user = repository.findByName(username);
        if (user != null) {
            if (encoder().matches(password, user.getPassword())) {
                String token = JwtTokenUtil.generateToken(user);
                Cookie cookie = new Cookie("access-token", token);
                cookie.setMaxAge(60 * 60);
                cookie.setHttpOnly(true);
                cookie.setPath("/");
                cookie.setDomain("university-rest-server.herokuapp.com");
                response.addCookie(cookie);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(200).build();
    }

    @PostMapping("/logout")
    public ResponseEntity<HttpStatus> logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie accessTokenCookie =new Cookie("access-token", "expired");
        accessTokenCookie.setValue("expired");
        accessTokenCookie.setMaxAge(0);
        accessTokenCookie.setPath("/");
        accessTokenCookie.setDomain("university-rest-server.herokuapp.com");
        accessTokenCookie.setHttpOnly(true);
        response.addCookie(accessTokenCookie);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/sign-up")
    public ResponseEntity<HttpStatus> registration(@Valid @RequestBody User newUser) {
        if (newUser.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        User existingUser = repository.findByNameAndEmail(newUser.getName(), newUser.getEmail());
        if (existingUser == null) {
            newUser.setAuthority(Authority.USER);
            newUser.setPassword(encoder().encode(newUser.getPassword()));
            repository.save(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).build();
        }
    }

    @GetMapping("/fast-access")
    public String getFastToken(HttpServletResponse response, @RequestParam String u, @RequestParam String p) {
        User user = repository.findByName(u);
        if (user == null) {
            return "No such user";
        }
        if (encoder().matches(p, user.getPassword())) {
            String token = JwtTokenUtil.generateToken(user);
            Cookie cookie = new Cookie("access-token", token);
            cookie.setMaxAge(60 * 60);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            cookie.setDomain("university-rest-server.herokuapp.com");
            response.addCookie(cookie);
            return "Successful,you got an access token to pass the security." +
                    "Your access token:\n" +
                    token;
        } else {
            return "Cannot access this account. Password is wrong";
        }
    }

    private User extractUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof DefaultOidcUser) {
            return UserDetailsExtractor.extract((DefaultOidcUser) principal);
        } else {
            if (principal instanceof String) {
                return User.Builder.anUser()
                        .withName((String) principal).build();
            } else {
                return (User) principal;

            }
        }
    }

    @Bean
    private PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

}