package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ImmunizationRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the ImmunizationRecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImmunizationRecordRepository extends MongoRepository<ImmunizationRecord, String> {

}
