package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ImmunizationItems;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the ImmunizationItems entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImmunizationItemsRepository extends MongoRepository<ImmunizationItems, String> {

}
