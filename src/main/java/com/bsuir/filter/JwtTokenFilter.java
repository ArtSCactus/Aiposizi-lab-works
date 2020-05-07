package com.bsuir.filter;

import com.bsuir.jwt.JwtTokenProvider;
import com.bsuir.exception.InvalidJwtAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtTokenFilter extends GenericFilterBean {
    private static final String COOKIE_NAME="access-token";


    private JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
        throws IOException, ServletException {

       Cookie cookie= WebUtils.getCookie((HttpServletRequest) req, COOKIE_NAME);
        if (cookie!=null){
            String token = cookie.getValue();
            try {
                if (token != null && jwtTokenProvider.validateToken(token)) {
                    Authentication auth = jwtTokenProvider.getAuthentication(token);
                    if (auth !=null) {
                        Authentication contextAuth = SecurityContextHolder.getContext().getAuthentication();
                        if (contextAuth == null || !SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
                            SecurityContextHolder.getContext().setAuthentication(auth);
                        }
                    }
                }
            } catch (InvalidJwtAuthenticationException ignored) {

            }
        }

        filterChain.doFilter(req, res);
    }

}