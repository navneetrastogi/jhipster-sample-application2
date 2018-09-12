package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Timeline;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Timeline entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TimelineRepository extends MongoRepository<Timeline, String> {

}
