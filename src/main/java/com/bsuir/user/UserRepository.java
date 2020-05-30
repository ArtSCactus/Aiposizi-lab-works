package com.bsuir.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author ArtSCactus
 * @version 1.0
 */
public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
    User findByName(String name);
    @Query("select id, authority, email, google_id, name, password from university.accounts where name=? and encode(digest(:password, 'sha256'), 'hex')")
    User findByNameAndPassword(String name, String password);
    @Modifying
    @Query("insert into university.accounts (id, authority, email, google_id, name, passowrd) values (:#{#user.id}, :#{#user.authority}, :#{#user.email}, :#{#user.googleId}, :#{#user.name}, encode(digest(:#{#user.password}, 'sha256'), 'hex'))")
    User save(@Param("user")User user);
}
