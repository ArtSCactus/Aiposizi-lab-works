package com.bsuir.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ArtSCactus
 * @version 1.0
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
   User findByGoogleUsername(String googleUsername);
}
