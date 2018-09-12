package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Incident;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Incident entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IncidentRepository extends MongoRepository<Incident, String> {

}
