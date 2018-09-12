package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Holiday;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Holiday entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HolidayRepository extends MongoRepository<Holiday, String> {

}
