package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.EventPhotos;
import io.github.jhipster.application.repository.EventPhotosRepository;
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
 * REST controller for managing EventPhotos.
 */
@RestController
@RequestMapping("/api")
public class EventPhotosResource {

    private final Logger log = LoggerFactory.getLogger(EventPhotosResource.class);

    private static final String ENTITY_NAME = "eventPhotos";

    private final EventPhotosRepository eventPhotosRepository;

    public EventPhotosResource(EventPhotosRepository eventPhotosRepository) {
        this.eventPhotosRepository = eventPhotosRepository;
    }

    /**
     * POST  /event-photos : Create a new eventPhotos.
     *
     * @param eventPhotos the eventPhotos to create
     * @return the ResponseEntity with status 201 (Created) and with body the new eventPhotos, or with status 400 (Bad Request) if the eventPhotos has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/event-photos")
    @Timed
    public ResponseEntity<EventPhotos> createEventPhotos(@RequestBody EventPhotos eventPhotos) throws URISyntaxException {
        log.debug("REST request to save EventPhotos : {}", eventPhotos);
        if (eventPhotos.getId() != null) {
            throw new BadRequestAlertException("A new eventPhotos cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EventPhotos result = eventPhotosRepository.save(eventPhotos);
        return ResponseEntity.created(new URI("/api/event-photos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /event-photos : Updates an existing eventPhotos.
     *
     * @param eventPhotos the eventPhotos to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated eventPhotos,
     * or with status 400 (Bad Request) if the eventPhotos is not valid,
     * or with status 500 (Internal Server Error) if the eventPhotos couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/event-photos")
    @Timed
    public ResponseEntity<EventPhotos> updateEventPhotos(@RequestBody EventPhotos eventPhotos) throws URISyntaxException {
        log.debug("REST request to update EventPhotos : {}", eventPhotos);
        if (eventPhotos.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EventPhotos result = eventPhotosRepository.save(eventPhotos);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, eventPhotos.getId().toString()))
            .body(result);
    }

    /**
     * GET  /event-photos : get all the eventPhotos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of eventPhotos in body
     */
    @GetMapping("/event-photos")
    @Timed
    public List<EventPhotos> getAllEventPhotos() {
        log.debug("REST request to get all EventPhotos");
        return eventPhotosRepository.findAll();
    }

    /**
     * GET  /event-photos/:id : get the "id" eventPhotos.
     *
     * @param id the id of the eventPhotos to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the eventPhotos, or with status 404 (Not Found)
     */
    @GetMapping("/event-photos/{id}")
    @Timed
    public ResponseEntity<EventPhotos> getEventPhotos(@PathVariable String id) {
        log.debug("REST request to get EventPhotos : {}", id);
        Optional<EventPhotos> eventPhotos = eventPhotosRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(eventPhotos);
    }

    /**
     * DELETE  /event-photos/:id : delete the "id" eventPhotos.
     *
     * @param id the id of the eventPhotos to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/event-photos/{id}")
    @Timed
    public ResponseEntity<Void> deleteEventPhotos(@PathVariable String id) {
        log.debug("REST request to delete EventPhotos : {}", id);

        eventPhotosRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
