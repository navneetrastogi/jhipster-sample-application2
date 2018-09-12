package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.TaskType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the TaskType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaskTypeRepository extends MongoRepository<TaskType, String> {

}
