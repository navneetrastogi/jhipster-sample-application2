package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Activity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ActivityRepository extends MongoRepository<Activity, String> {

}
