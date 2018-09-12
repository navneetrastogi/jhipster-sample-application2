package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Media;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Media entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MediaRepository extends MongoRepository<Media, String> {

}
