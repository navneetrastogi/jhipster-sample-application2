package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Center;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Center entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CenterRepository extends MongoRepository<Center, String> {

}
