package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.ScheduleItem;
import io.github.jhipster.application.repository.ScheduleItemRepository;
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
 * REST controller for managing ScheduleItem.
 */
@RestController
@RequestMapping("/api")
public class ScheduleItemResource {

    private final Logger log = LoggerFactory.getLogger(ScheduleItemResource.class);

    private static final String ENTITY_NAME = "scheduleItem";

    private final ScheduleItemRepository scheduleItemRepository;

    public ScheduleItemResource(ScheduleItemRepository scheduleItemRepository) {
        this.scheduleItemRepository = scheduleItemRepository;
    }

    /**
     * POST  /schedule-items : Create a new scheduleItem.
     *
     * @param scheduleItem the scheduleItem to create
     * @return the ResponseEntity with status 201 (Created) and with body the new scheduleItem, or with status 400 (Bad Request) if the scheduleItem has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/schedule-items")
    @Timed
    public ResponseEntity<ScheduleItem> createScheduleItem(@RequestBody ScheduleItem scheduleItem) throws URISyntaxException {
        log.debug("REST request to save ScheduleItem : {}", scheduleItem);
        if (scheduleItem.getId() != null) {
            throw new BadRequestAlertException("A new scheduleItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ScheduleItem result = scheduleItemRepository.save(scheduleItem);
        return ResponseEntity.created(new URI("/api/schedule-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /schedule-items : Updates an existing scheduleItem.
     *
     * @param scheduleItem the scheduleItem to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated scheduleItem,
     * or with status 400 (Bad Request) if the scheduleItem is not valid,
     * or with status 500 (Internal Server Error) if the scheduleItem couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/schedule-items")
    @Timed
    public ResponseEntity<ScheduleItem> updateScheduleItem(@RequestBody ScheduleItem scheduleItem) throws URISyntaxException {
        log.debug("REST request to update ScheduleItem : {}", scheduleItem);
        if (scheduleItem.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ScheduleItem result = scheduleItemRepository.save(scheduleItem);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, scheduleItem.getId().toString()))
            .body(result);
    }

    /**
     * GET  /schedule-items : get all the scheduleItems.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of scheduleItems in body
     */
    @GetMapping("/schedule-items")
    @Timed
    public List<ScheduleItem> getAllScheduleItems() {
        log.debug("REST request to get all ScheduleItems");
        return scheduleItemRepository.findAll();
    }

    /**
     * GET  /schedule-items/:id : get the "id" scheduleItem.
     *
     * @param id the id of the scheduleItem to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the scheduleItem, or with status 404 (Not Found)
     */
    @GetMapping("/schedule-items/{id}")
    @Timed
    public ResponseEntity<ScheduleItem> getScheduleItem(@PathVariable String id) {
        log.debug("REST request to get ScheduleItem : {}", id);
        Optional<ScheduleItem> scheduleItem = scheduleItemRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(scheduleItem);
    }

    /**
     * DELETE  /schedule-items/:id : delete the "id" scheduleItem.
     *
     * @param id the id of the scheduleItem to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/schedule-items/{id}")
    @Timed
    public ResponseEntity<Void> deleteScheduleItem(@PathVariable String id) {
        log.debug("REST request to delete ScheduleItem : {}", id);

        scheduleItemRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
