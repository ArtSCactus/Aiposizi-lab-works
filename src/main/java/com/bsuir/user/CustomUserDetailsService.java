package com.bsuir.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author ArtSCactus
 * @version 1.0
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByName(username);
    }

    public UserDetails loadUserByEmail(String email){
        return repository.findByEmail(email);
    }
}
