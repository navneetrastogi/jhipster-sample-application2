package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Permission;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Permission entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PermissionRepository extends MongoRepository<Permission, String> {

}
