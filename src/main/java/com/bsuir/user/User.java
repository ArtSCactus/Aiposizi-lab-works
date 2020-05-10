package com.bsuir.user;

import lombok.Data;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.Collections;

/**
 * @author ArtSCactus
 * @version 1.0
 */
@Entity
@Table(name = "accounts", schema = "university")
@Data
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="google_id", length = 2000)
    private String googleId;
    @Column(name = "name")
    @NotEmpty
    @NotNull
    @Pattern(regexp = "[A-zА-я]{1,30}")
    private String name;
    @Column(name = "email")
    @Pattern(regexp = "[A-zА-я_]{1,20}@[A-z]{1,10}.(com|org|ru|by)")
    private String email;
    @Column(name = "password")
    @Nullable
    @Pattern(regexp="[^<>\"\\s]{3,45}")
    private String password;
    @Column(name= "authority")
    @Enumerated(EnumType.STRING)
    private Authority authority;

    public User() {
    }


    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public static final class Builder {
        private User user;

        private Builder() {
            user = new User();
        }

        public static Builder anUser() {
            return new Builder();
        }

        public Builder withId(Long id) {
            user.setId(id);
            return this;
        }

        public Builder withGoogleId(String id){
            user.setGoogleId(id);
            return this;
        }

        public Builder withName(String name) {
            user.setName(name);
            return this;
        }

        public Builder withEmail(String email) {
            user.setEmail(email);
            return this;
        }

        public Builder withPassword(String password) {
            user.setPassword(password);
            return this;
        }

        public Builder withRole(Authority authority) {
            user.setAuthority(authority);
            return this;
        }

        public User build() {
            return user;
        }
    }
}
