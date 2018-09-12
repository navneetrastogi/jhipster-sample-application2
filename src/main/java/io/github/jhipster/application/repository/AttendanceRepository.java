package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Attendance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Attendance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AttendanceRepository extends MongoRepository<Attendance, String> {

}
