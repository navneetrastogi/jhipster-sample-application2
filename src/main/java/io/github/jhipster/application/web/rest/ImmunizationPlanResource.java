package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.ImmunizationPlan;
import io.github.jhipster.application.repository.ImmunizationPlanRepository;
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
 * REST controller for managing ImmunizationPlan.
 */
@RestController
@RequestMapping("/api")
public class ImmunizationPlanResource {

    private final Logger log = LoggerFactory.getLogger(ImmunizationPlanResource.class);

    private static final String ENTITY_NAME = "immunizationPlan";

    private final ImmunizationPlanRepository immunizationPlanRepository;

    public ImmunizationPlanResource(ImmunizationPlanRepository immunizationPlanRepository) {
        this.immunizationPlanRepository = immunizationPlanRepository;
    }

    /**
     * POST  /immunization-plans : Create a new immunizationPlan.
     *
     * @param immunizationPlan the immunizationPlan to create
     * @return the ResponseEntity with status 201 (Created) and with body the new immunizationPlan, or with status 400 (Bad Request) if the immunizationPlan has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/immunization-plans")
    @Timed
    public ResponseEntity<ImmunizationPlan> createImmunizationPlan(@RequestBody ImmunizationPlan immunizationPlan) throws URISyntaxException {
        log.debug("REST request to save ImmunizationPlan : {}", immunizationPlan);
        if (immunizationPlan.getId() != null) {
            throw new BadRequestAlertException("A new immunizationPlan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ImmunizationPlan result = immunizationPlanRepository.save(immunizationPlan);
        return ResponseEntity.created(new URI("/api/immunization-plans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /immunization-plans : Updates an existing immunizationPlan.
     *
     * @param immunizationPlan the immunizationPlan to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated immunizationPlan,
     * or with status 400 (Bad Request) if the immunizationPlan is not valid,
     * or with status 500 (Internal Server Error) if the immunizationPlan couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/immunization-plans")
    @Timed
    public ResponseEntity<ImmunizationPlan> updateImmunizationPlan(@RequestBody ImmunizationPlan immunizationPlan) throws URISyntaxException {
        log.debug("REST request to update ImmunizationPlan : {}", immunizationPlan);
        if (immunizationPlan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ImmunizationPlan result = immunizationPlanRepository.save(immunizationPlan);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, immunizationPlan.getId().toString()))
            .body(result);
    }

    /**
     * GET  /immunization-plans : get all the immunizationPlans.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of immunizationPlans in body
     */
    @GetMapping("/immunization-plans")
    @Timed
    public List<ImmunizationPlan> getAllImmunizationPlans() {
        log.debug("REST request to get all ImmunizationPlans");
        return immunizationPlanRepository.findAll();
    }

    /**
     * GET  /immunization-plans/:id : get the "id" immunizationPlan.
     *
     * @param id the id of the immunizationPlan to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the immunizationPlan, or with status 404 (Not Found)
     */
    @GetMapping("/immunization-plans/{id}")
    @Timed
    public ResponseEntity<ImmunizationPlan> getImmunizationPlan(@PathVariable String id) {
        log.debug("REST request to get ImmunizationPlan : {}", id);
        Optional<ImmunizationPlan> immunizationPlan = immunizationPlanRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(immunizationPlan);
    }

    /**
     * DELETE  /immunization-plans/:id : delete the "id" immunizationPlan.
     *
     * @param id the id of the immunizationPlan to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/immunization-plans/{id}")
    @Timed
    public ResponseEntity<Void> deleteImmunizationPlan(@PathVariable String id) {
        log.debug("REST request to delete ImmunizationPlan : {}", id);

        immunizationPlanRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
