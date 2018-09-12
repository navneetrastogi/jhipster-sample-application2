package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Schedule entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ScheduleRepository extends MongoRepository<Schedule, String> {

}
