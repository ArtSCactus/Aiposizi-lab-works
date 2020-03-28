package com.bsuir.user;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author ArtSCactus
 * @version 1.0
 */
@Entity
@Table(name = "users")
public class User{// implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String username;
    private String password;
    private boolean active;
    private String googleName;
    private String googleUsername;
    /*@ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "role", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;*/

  /*  @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }*/

    //@Override
    public String getPassword() {
        return password;
    }

   // @Override
    public String getUsername() {
        return name;
    }

   // @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    //@Override
    public boolean isAccountNonLocked() {
        return false;
    }

    //@Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    //@Override
    public boolean isEnabled() {
        return false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getGoogleName() {
        return googleName;
    }

    public String getGoogleUsername() {
        return googleUsername;
    }

   /* public Set<Role> getRoles() {
        return roles;
    }*/

    /*public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
*/
    public void setGoogleName(String googleName) {
        this.googleName = googleName;
    }

    public void setGoogleUsername(String googleUsername) {
        this.googleUsername = googleUsername;
    }


    public void setUsername(String userName) {
        this.username = userName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return isActive() == user.isActive() &&
                Objects.equals(getId(), user.getId()) &&
                Objects.equals(getName(), user.getName()) &&
                Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getGoogleName(), user.getGoogleName()) &&
                Objects.equals(getGoogleUsername(), user.getGoogleUsername());// &&
             //   Objects.equals(getRoles(), user.getRoles());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getUsername(), getPassword(), isActive(), getGoogleName(), getGoogleUsername()); //getRoles());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", googleName='" + googleName + '\'' +
                ", googleUsername='" + googleUsername + '\'' +
              //  ", roles=" + roles +
                '}';
    }
}
