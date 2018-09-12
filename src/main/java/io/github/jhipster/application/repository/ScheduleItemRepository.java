package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ScheduleItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the ScheduleItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ScheduleItemRepository extends MongoRepository<ScheduleItem, String> {

}
