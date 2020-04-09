package com.bsuir.student;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author ArtSCactus
 * @version 1.0
 */
@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    Optional<Student> findById(Long id);
}
