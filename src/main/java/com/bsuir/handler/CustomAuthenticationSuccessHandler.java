package com.bsuir.handler;

import com.bsuir.jwt.JwtTokenUtil;
import com.bsuir.user.Authority;
import com.bsuir.user.User;
import com.bsuir.user.CustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private CustomUserRepository repo;

    public CustomAuthenticationSuccessHandler() {
        super();
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException, IOException {
        if (response.isCommitted()) {
            return;
        }
        DefaultOidcUser oidcUser = (DefaultOidcUser) authentication.getPrincipal();
        User existingUser = repo.findByEmail(oidcUser.getEmail());
        if (existingUser == null) {
            User user = User.Builder.anUser()
                    .withName(oidcUser.getGivenName())
                    .withGoogleId(oidcUser.getIdToken().getTokenValue())
                    .withEmail(oidcUser.getEmail())
                    .withRole(Authority.USER)
                    .build();
            repo.save(user);
           UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());
           SecurityContextHolder.getContext().setAuthentication(token);
            User userForId = repo.findByName(user.getName());
            Cookie cookie = generateToken(userForId);
            response.addCookie(cookie);
        } else {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(existingUser, "", existingUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(token);
            Cookie cookie = generateToken(existingUser);
            response.addCookie(cookie);

        }
        response.sendRedirect("https://university-view.herokuapp.com");
    }

    private Cookie generateToken(User user){
        String token = JwtTokenUtil.generateToken(user);
        Cookie cookie = new Cookie("access-token", token);
        cookie.setMaxAge(60 * 60);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setDomain("university-rest-server.herokuapp.com");
        return cookie;
    }
}