package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.ImmunizationItems;
import io.github.jhipster.application.repository.ImmunizationItemsRepository;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ImmunizationItems.
 */
@RestController
@RequestMapping("/api")
public class ImmunizationItemsResource {

    private final Logger log = LoggerFactory.getLogger(ImmunizationItemsResource.class);

    private static final String ENTITY_NAME = "immunizationItems";

    private final ImmunizationItemsRepository immunizationItemsRepository;

    public ImmunizationItemsResource(ImmunizationItemsRepository immunizationItemsRepository) {
        this.immunizationItemsRepository = immunizationItemsRepository;
    }

    /**
     * POST  /immunization-items : Create a new immunizationItems.
     *
     * @param immunizationItems the immunizationItems to create
     * @return the ResponseEntity with status 201 (Created) and with body the new immunizationItems, or with status 400 (Bad Request) if the immunizationItems has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/immunization-items")
    @Timed
    public ResponseEntity<ImmunizationItems> createImmunizationItems(@RequestBody ImmunizationItems immunizationItems) throws URISyntaxException {
        log.debug("REST request to save ImmunizationItems : {}", immunizationItems);
        if (immunizationItems.getId() != null) {
            throw new BadRequestAlertException("A new immunizationItems cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ImmunizationItems result = immunizationItemsRepository.save(immunizationItems);
        return ResponseEntity.created(new URI("/api/immunization-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /immunization-items : Updates an existing immunizationItems.
     *
     * @param immunizationItems the immunizationItems to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated immunizationItems,
     * or with status 400 (Bad Request) if the immunizationItems is not valid,
     * or with status 500 (Internal Server Error) if the immunizationItems couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/immunization-items")
    @Timed
    public ResponseEntity<ImmunizationItems> updateImmunizationItems(@RequestBody ImmunizationItems immunizationItems) throws URISyntaxException {
        log.debug("REST request to update ImmunizationItems : {}", immunizationItems);
        if (immunizationItems.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ImmunizationItems result = immunizationItemsRepository.save(immunizationItems);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, immunizationItems.getId().toString()))
            .body(result);
    }

    /**
     * GET  /immunization-items : get all the immunizationItems.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of immunizationItems in body
     */
    @GetMapping("/immunization-items")
    @Timed
    public List<ImmunizationItems> getAllImmunizationItems() {
        log.debug("REST request to get all ImmunizationItems");
        return immunizationItemsRepository.findAll();
    }

    /**
     * GET  /immunization-items/:id : get the "id" immunizationItems.
     *
     * @param id the id of the immunizationItems to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the immunizationItems, or with status 404 (Not Found)
     */
    @GetMapping("/immunization-items/{id}")
    @Timed
    public ResponseEntity<ImmunizationItems> getImmunizationItems(@PathVariable String id) {
        log.debug("REST request to get ImmunizationItems : {}", id);
        Optional<ImmunizationItems> immunizationItems = immunizationItemsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(immunizationItems);
    }

    /**
     * DELETE  /immunization-items/:id : delete the "id" immunizationItems.
     *
     * @param id the id of the immunizationItems to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/immunization-items/{id}")
    @Timed
    public ResponseEntity<Void> deleteImmunizationItems(@PathVariable String id) {
        log.debug("REST request to delete ImmunizationItems : {}", id);

        immunizationItemsRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
