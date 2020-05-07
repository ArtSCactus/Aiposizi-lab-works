package com.bsuir.user;

import com.bsuir.exception.InvalidJwtAuthenticationException;
import com.bsuir.jwt.JwtTokenProvider;
import com.bsuir.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserRepository repository;

    @GetMapping("/oauth-2-endpoint")
    public void redirectToApplication(HttpServletResponse response) throws IOException {
       User user = extractUser();
        String token = JwtTokenUtil.generateToken(user);
        Cookie cookie = new Cookie("access-token", token);
        cookie.setMaxAge(2 * 60 * 60);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        response.sendRedirect("http://localhost:8080");
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
                    cookie.setMaxAge(2 * 60 * 60);
                    cookie.setHttpOnly(true);
                    cookie.setPath("/");
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
        User user = repository.findByNameAndPassword(username, password);
        if (user != null) {
            String token = JwtTokenUtil.generateToken(user);
            Cookie cookie = new Cookie("access-token", token);
            cookie.setMaxAge(2 * 60 * 60);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            response.addCookie(cookie);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/application-logout")
    public void appLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie accessTokenCookie = new Cookie("access-token","expired");//WebUtils.getCookie(request, "access-token");
        accessTokenCookie.setValue("expired");
        accessTokenCookie.setMaxAge(0);
        accessTokenCookie.setPath("/");
        accessTokenCookie.setHttpOnly(true);
        response.addCookie(accessTokenCookie);
        HttpSession session= request.getSession(false);
        SecurityContextHolder.clearContext();
        if(session != null) {
            session.invalidate();
        }
        response.sendRedirect("http://localhost:8080");
    }

    @PostMapping("/logout")
    public ResponseEntity<HttpStatus> logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie accessTokenCookie = new Cookie("access-token","expired");//WebUtils.getCookie(request, "access-token");
            accessTokenCookie.setValue("expired");
            accessTokenCookie.setMaxAge(0);
            accessTokenCookie.setPath("/");
            accessTokenCookie.setHttpOnly(true);
            response.addCookie(accessTokenCookie);
        HttpSession session= request.getSession(false);
        SecurityContextHolder.clearContext();
        if(session != null) {
            session.invalidate();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/fast-access")
    public String getFastToken(HttpServletResponse response, @RequestParam String u, @RequestParam String p) {
        User user = repository.findByName(u);
        if (user == null) {
            return "No such user";
        }
        if (user.getPassword().equals(p)) {
            String token = JwtTokenUtil.generateToken(user);
            Cookie cookie = new Cookie("access-token", token);
            cookie.setMaxAge(2 * 60 * 60);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            response.addCookie(cookie);
            return "Successful,you got an access token to pass the security." +
                    "Your access token:\n" +
                    token;
        } else {
            return "Cannot access this account. Password is wrong";
        }
    }

    private User extractUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof DefaultOidcUser){
           return UserDetailsExtractor.extract((DefaultOidcUser) principal);
        } else {
            if (principal instanceof String) {
                return  User.Builder.anUser()
                        .withName((String) principal).build();
            } else {
                return (User) principal;

            }
        }
    }

}