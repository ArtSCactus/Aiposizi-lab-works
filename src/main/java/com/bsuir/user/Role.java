package com.bsuir.user;

/**
 * @author ArtSCactus
 * @version 1.0
 */
public enum Role //implements GrantedAuthority
{
    USER, ADMIN;

   // @Override
    public String getAuthority()
    {
        return name();
    }
}
