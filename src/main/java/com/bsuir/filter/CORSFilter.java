package com.bsuir.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ArtSCactus
 * @version 1.0
 */
@Component
public class CORSFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
     /*   response.addHeader("Access-Control-Allow-Origin", "http://university-view.herokuapp.com");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type, http://university-view.herokuapp.com");
        response.addHeader("Access-Control-Allow-Methods", "DELETE, POST, GET, OPTIONS");*/
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
