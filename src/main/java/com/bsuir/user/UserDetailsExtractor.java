package com.bsuir.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;

/**
 * @author ArtSCactus
 * @version 1.0
 */
public class UserDetailsExtractor {

public static User extract(DefaultOidcUser oidcUser){
    return  User.Builder.anUser()
            .withName(oidcUser.getName())
            .withEmail(oidcUser.getEmail())
            .withGoogleId(oidcUser.getIdToken().getTokenValue())
            .build();
}
}
