package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.KudosRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the KudosRecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KudosRecordRepository extends MongoRepository<KudosRecord, String> {

}
