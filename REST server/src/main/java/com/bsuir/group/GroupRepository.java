package com.bsuir.group;

import com.bsuir.group.StudentGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author ArtSCactus
 * @version 1.0
 */
@Repository
public interface GroupRepository extends CrudRepository<StudentGroup, Long> {
    Optional<StudentGroup> findById(Long id);

}
