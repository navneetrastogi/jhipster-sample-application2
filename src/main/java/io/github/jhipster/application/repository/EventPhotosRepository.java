package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.EventPhotos;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the EventPhotos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EventPhotosRepository extends MongoRepository<EventPhotos, String> {

}
