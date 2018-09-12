package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Milestone;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Milestone entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MilestoneRepository extends MongoRepository<Milestone, String> {

}
