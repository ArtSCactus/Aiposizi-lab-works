package com.bsuir.config;

import com.bsuir.handler.CustomAuthenticationSuccessHandler;
import com.bsuir.jwt.JwtConfigurer;
import com.bsuir.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * @author ArtSCactus
 * @version 1.0
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtTokenProvider jwtTokenProvider;
    private String[] MAIN_DATA_URIS = new String[]{"/students/**",
            "/groups/**",
            "/lessons/**",
            "/user/**"
            ,"/teachers/**"};

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/auth/**")
                .permitAll()
                .antMatchers(HttpMethod.PUT, MAIN_DATA_URIS).hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, MAIN_DATA_URIS)
                .hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, MAIN_DATA_URIS).hasAnyAuthority("ADMIN", "USER")
                .and().oauth2Login()
                .successHandler(successHandler())
                .and()
                .cors().configurationSource(corsConfigurationSource())
                .and().apply(new JwtConfigurer(jwtTokenProvider))
                .and()
        .csrf().disable()
        .logout()
                .logoutUrl("/auth/logout")
                .clearAuthentication(true)
        .deleteCookies("access-token", "JSESSIONID","XSRF-TOKEN")
        .invalidateHttpSession(true)
                .permitAll();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080", "https://university-view.herokuapp.com"));
        configuration.setAllowedMethods(Arrays.asList("OPTIONS", "GET", "POST", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("application/json"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    @Bean
    public SimpleUrlAuthenticationSuccessHandler successHandler() {
        return new CustomAuthenticationSuccessHandler();
    }
}
