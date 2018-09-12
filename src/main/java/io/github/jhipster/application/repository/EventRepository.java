package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Event entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EventRepository extends MongoRepository<Event, String> {

}
