package com.bsuir.subject;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author ArtSCactus
 * @version 1.0
 */
@Repository
public interface SubjectRepository extends CrudRepository<Subject, Long> {
    Optional<Subject> findById(Long id);
}
