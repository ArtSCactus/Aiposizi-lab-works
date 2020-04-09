package com.bsuir.config;

/**
 * @author ArtSCactus
 * @version 1.0
 */
//@Configuration
//@EnableOAuth2Client
public class SpringSecurityConfig{// extends WebSecurityConfigurerAdapter {
/*

    @Qualifier("oauth2ClientContext")
    @Autowired
    private OAuth2ClientContext oAuth2ClientContext;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    PasswordEncoder passwordEncoder() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("{noop}root").roles("ADMIN")
                .and()
                .withUser("user").password("{noop}pass").roles("USER");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/teachers/**").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/teachers/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/teachers/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/teachers/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/teachers/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/teachers/**").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }
*/


}
