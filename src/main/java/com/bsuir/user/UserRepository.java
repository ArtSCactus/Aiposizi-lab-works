package com.bsuir.user;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ArtSCactus
 * @version 1.0
 */
public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
    User findByName(String name);
    User findByNameAndPassword(String name, String password);
}
