package com.bsuir.user;

/**
 * @author ArtSCactus
 * @version 1.0
 */

import lombok.Data;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;

import java.io.Serializable;

/**This class is designed as transfer object for user details.
 * JSONUser is represents a similar data as User object, but except the confidential data,
 * such as passwords, third-party access tokens etc.
 */
@Data
public class JSONUser implements Serializable {
    private String name;
    private String email;
    private Authority authority;

     public static JSONUser fromUser(User user){
         return JSONUser.Builder.aJSONUser()
                 .withName(user.getName())
                 .withEmail(user.getEmail())
                 .withAuthority(user.getAuthority())
                 .build();
     }

     public static JSONUser fromOidcUser(DefaultOidcUser user){
         return JSONUser.Builder.aJSONUser()
                 .withName(user.getGivenName())
                 .withEmail(user.getEmail())
                 .withAuthority((Authority) user.getAuthorities().iterator().next())
                 .build();
     }

    public static final class Builder {
        private JSONUser jSONUser;

        private Builder() {
            jSONUser = new JSONUser();
        }

        public static Builder aJSONUser() {
            return new Builder();
        }

        public Builder withName(String name) {
            jSONUser.setName(name);
            return this;
        }

        public Builder withEmail(String email) {
            jSONUser.setEmail(email);
            return this;
        }

        public Builder withAuthority(Authority authority) {
            jSONUser.setAuthority(authority);
            return this;
        }

        public JSONUser build() {
            return jSONUser;
        }
    }
}
