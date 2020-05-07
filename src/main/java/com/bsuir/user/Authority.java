package com.bsuir.user;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ArtSCactus
 * @version 1.0
 */
public enum Authority implements Role, GrantedAuthority, Serializable {
    ADMIN, USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
