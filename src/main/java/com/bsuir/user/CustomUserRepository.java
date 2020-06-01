package com.bsuir.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ArtSCactus
 * @version 1.0
 */
@Repository
public interface CustomUserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);

    User findByName(String name);

    User findByNameAndEmail(String name, String email);

    User findUserById(Long id);
}
