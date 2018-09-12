package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ImmunizationPlan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the ImmunizationPlan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImmunizationPlanRepository extends MongoRepository<ImmunizationPlan, String> {

}
