package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.Instruction;
import io.github.jhipster.application.repository.InstructionRepository;
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
 * REST controller for managing Instruction.
 */
@RestController
@RequestMapping("/api")
public class InstructionResource {

    private final Logger log = LoggerFactory.getLogger(InstructionResource.class);

    private static final String ENTITY_NAME = "instruction";

    private final InstructionRepository instructionRepository;

    public InstructionResource(InstructionRepository instructionRepository) {
        this.instructionRepository = instructionRepository;
    }

    /**
     * POST  /instructions : Create a new instruction.
     *
     * @param instruction the instruction to create
     * @return the ResponseEntity with status 201 (Created) and with body the new instruction, or with status 400 (Bad Request) if the instruction has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/instructions")
    @Timed
    public ResponseEntity<Instruction> createInstruction(@RequestBody Instruction instruction) throws URISyntaxException {
        log.debug("REST request to save Instruction : {}", instruction);
        if (instruction.getId() != null) {
            throw new BadRequestAlertException("A new instruction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Instruction result = instructionRepository.save(instruction);
        return ResponseEntity.created(new URI("/api/instructions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /instructions : Updates an existing instruction.
     *
     * @param instruction the instruction to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated instruction,
     * or with status 400 (Bad Request) if the instruction is not valid,
     * or with status 500 (Internal Server Error) if the instruction couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/instructions")
    @Timed
    public ResponseEntity<Instruction> updateInstruction(@RequestBody Instruction instruction) throws URISyntaxException {
        log.debug("REST request to update Instruction : {}", instruction);
        if (instruction.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Instruction result = instructionRepository.save(instruction);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, instruction.getId().toString()))
            .body(result);
    }

    /**
     * GET  /instructions : get all the instructions.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of instructions in body
     */
    @GetMapping("/instructions")
    @Timed
    public List<Instruction> getAllInstructions() {
        log.debug("REST request to get all Instructions");
        return instructionRepository.findAll();
    }

    /**
     * GET  /instructions/:id : get the "id" instruction.
     *
     * @param id the id of the instruction to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the instruction, or with status 404 (Not Found)
     */
    @GetMapping("/instructions/{id}")
    @Timed
    public ResponseEntity<Instruction> getInstruction(@PathVariable String id) {
        log.debug("REST request to get Instruction : {}", id);
        Optional<Instruction> instruction = instructionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(instruction);
    }

    /**
     * DELETE  /instructions/:id : delete the "id" instruction.
     *
     * @param id the id of the instruction to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/instructions/{id}")
    @Timed
    public ResponseEntity<Void> deleteInstruction(@PathVariable String id) {
        log.debug("REST request to delete Instruction : {}", id);

        instructionRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
