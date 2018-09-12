package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Conversation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Conversation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConversationRepository extends MongoRepository<Conversation, String> {

}
