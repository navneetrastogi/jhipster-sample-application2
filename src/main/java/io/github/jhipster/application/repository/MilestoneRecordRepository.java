package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.MilestoneRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the MilestoneRecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MilestoneRecordRepository extends MongoRepository<MilestoneRecord, String> {

}
