package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Instruction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Instruction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InstructionRepository extends MongoRepository<Instruction, String> {

}
