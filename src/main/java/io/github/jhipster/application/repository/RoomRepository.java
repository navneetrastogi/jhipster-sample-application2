package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Room entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RoomRepository extends MongoRepository<Room, String> {

}
