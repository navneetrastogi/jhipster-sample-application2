package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.IncidentRecord;
import io.github.jhipster.application.repository.IncidentRecordRepository;
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
 * REST controller for managing IncidentRecord.
 */
@RestController
@RequestMapping("/api")
public class IncidentRecordResource {

    private final Logger log = LoggerFactory.getLogger(IncidentRecordResource.class);

    private static final String ENTITY_NAME = "incidentRecord";

    private final IncidentRecordRepository incidentRecordRepository;

    public IncidentRecordResource(IncidentRecordRepository incidentRecordRepository) {
        this.incidentRecordRepository = incidentRecordRepository;
    }

    /**
     * POST  /incident-records : Create a new incidentRecord.
     *
     * @param incidentRecord the incidentRecord to create
     * @return the ResponseEntity with status 201 (Created) and with body the new incidentRecord, or with status 400 (Bad Request) if the incidentRecord has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/incident-records")
    @Timed
    public ResponseEntity<IncidentRecord> createIncidentRecord(@RequestBody IncidentRecord incidentRecord) throws URISyntaxException {
        log.debug("REST request to save IncidentRecord : {}", incidentRecord);
        if (incidentRecord.getId() != null) {
            throw new BadRequestAlertException("A new incidentRecord cannot already have an ID", ENTITY_NAME, "idexists");
        }
        IncidentRecord result = incidentRecordRepository.save(incidentRecord);
        return ResponseEntity.created(new URI("/api/incident-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /incident-records : Updates an existing incidentRecord.
     *
     * @param incidentRecord the incidentRecord to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated incidentRecord,
     * or with status 400 (Bad Request) if the incidentRecord is not valid,
     * or with status 500 (Internal Server Error) if the incidentRecord couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/incident-records")
    @Timed
    public ResponseEntity<IncidentRecord> updateIncidentRecord(@RequestBody IncidentRecord incidentRecord) throws URISyntaxException {
        log.debug("REST request to update IncidentRecord : {}", incidentRecord);
        if (incidentRecord.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        IncidentRecord result = incidentRecordRepository.save(incidentRecord);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, incidentRecord.getId().toString()))
            .body(result);
    }

    /**
     * GET  /incident-records : get all the incidentRecords.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of incidentRecords in body
     */
    @GetMapping("/incident-records")
    @Timed
    public List<IncidentRecord> getAllIncidentRecords() {
        log.debug("REST request to get all IncidentRecords");
        return incidentRecordRepository.findAll();
    }

    /**
     * GET  /incident-records/:id : get the "id" incidentRecord.
     *
     * @param id the id of the incidentRecord to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the incidentRecord, or with status 404 (Not Found)
     */
    @GetMapping("/incident-records/{id}")
    @Timed
    public ResponseEntity<IncidentRecord> getIncidentRecord(@PathVariable String id) {
        log.debug("REST request to get IncidentRecord : {}", id);
        Optional<IncidentRecord> incidentRecord = incidentRecordRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(incidentRecord);
    }

    /**
     * DELETE  /incident-records/:id : delete the "id" incidentRecord.
     *
     * @param id the id of the incidentRecord to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/incident-records/{id}")
    @Timed
    public ResponseEntity<Void> deleteIncidentRecord(@PathVariable String id) {
        log.debug("REST request to delete IncidentRecord : {}", id);

        incidentRecordRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
