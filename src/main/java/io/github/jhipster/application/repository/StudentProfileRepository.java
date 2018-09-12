package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.StudentProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the StudentProfile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StudentProfileRepository extends MongoRepository<StudentProfile, String> {

}
