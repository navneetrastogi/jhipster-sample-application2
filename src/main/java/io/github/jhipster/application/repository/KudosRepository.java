package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Kudos;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Kudos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KudosRepository extends MongoRepository<Kudos, String> {

}
