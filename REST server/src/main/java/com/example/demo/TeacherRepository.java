package com.example.demo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author ArtSCactus
 * @version 1.0
 */
@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {
Optional<Teacher> findById(Long id);
}
