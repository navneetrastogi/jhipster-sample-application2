package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ActivityType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the ActivityType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ActivityTypeRepository extends MongoRepository<ActivityType, String> {

}
