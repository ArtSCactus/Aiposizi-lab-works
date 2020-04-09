package com.bsuir.lesson;

import com.bsuir.lesson.Lesson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author ArtSCactus
 * @version 1.0
 */
@Repository
public interface LessonRepository extends CrudRepository<Lesson, Long> {
    Optional<Lesson> findById(Long id);
}
