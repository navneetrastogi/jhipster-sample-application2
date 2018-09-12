package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Feature;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Feature entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FeatureRepository extends MongoRepository<Feature, String> {

}
