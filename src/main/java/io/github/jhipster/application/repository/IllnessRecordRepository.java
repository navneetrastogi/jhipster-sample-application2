package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.IllnessRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the IllnessRecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IllnessRecordRepository extends MongoRepository<IllnessRecord, String> {

}
