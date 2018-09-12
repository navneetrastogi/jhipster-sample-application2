package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Teacher entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TeacherRepository extends MongoRepository<Teacher, String> {

}
