package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.IncidentRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the IncidentRecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IncidentRecordRepository extends MongoRepository<IncidentRecord, String> {

}
