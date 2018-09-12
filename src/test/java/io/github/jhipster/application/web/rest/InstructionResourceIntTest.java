package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplication2App;

import io.github.jhipster.application.domain.Instruction;
import io.github.jhipster.application.repository.InstructionRepository;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;


import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the InstructionResource REST controller.
 *
 * @see InstructionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplication2App.class)
public class InstructionResourceIntTest {

    private static final Long DEFAULT_SCHEDULE_ITEM_ID = 1L;
    private static final Long UPDATED_SCHEDULE_ITEM_ID = 2L;

    private static final String DEFAULT_VIDEO_URL = "AAAAAAAAAA";
    private static final String UPDATED_VIDEO_URL = "BBBBBBBBBB";

    private static final String DEFAULT_PHOTO_UR_LS = "AAAAAAAAAA";
    private static final String UPDATED_PHOTO_UR_LS = "BBBBBBBBBB";

    private static final String DEFAULT_INSTRUCTION = "AAAAAAAAAA";
    private static final String UPDATED_INSTRUCTION = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    @Autowired
    private InstructionRepository instructionRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restInstructionMockMvc;

    private Instruction instruction;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final InstructionResource instructionResource = new InstructionResource(instructionRepository);
        this.restInstructionMockMvc = MockMvcBuilders.standaloneSetup(instructionResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Instruction createEntity() {
        Instruction instruction = new Instruction()
            .scheduleItemId(DEFAULT_SCHEDULE_ITEM_ID)
            .videoURL(DEFAULT_VIDEO_URL)
            .photoURLs(DEFAULT_PHOTO_UR_LS)
            .instruction(DEFAULT_INSTRUCTION)
            .title(DEFAULT_TITLE);
        return instruction;
    }

    @Before
    public void initTest() {
        instructionRepository.deleteAll();
        instruction = createEntity();
    }

    @Test
    public void createInstruction() throws Exception {
        int databaseSizeBeforeCreate = instructionRepository.findAll().size();

        // Create the Instruction
        restInstructionMockMvc.perform(post("/api/instructions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(instruction)))
            .andExpect(status().isCreated());

        // Validate the Instruction in the database
        List<Instruction> instructionList = instructionRepository.findAll();
        assertThat(instructionList).hasSize(databaseSizeBeforeCreate + 1);
        Instruction testInstruction = instructionList.get(instructionList.size() - 1);
        assertThat(testInstruction.getScheduleItemId()).isEqualTo(DEFAULT_SCHEDULE_ITEM_ID);
        assertThat(testInstruction.getVideoURL()).isEqualTo(DEFAULT_VIDEO_URL);
        assertThat(testInstruction.getPhotoURLs()).isEqualTo(DEFAULT_PHOTO_UR_LS);
        assertThat(testInstruction.getInstruction()).isEqualTo(DEFAULT_INSTRUCTION);
        assertThat(testInstruction.getTitle()).isEqualTo(DEFAULT_TITLE);
    }

    @Test
    public void createInstructionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = instructionRepository.findAll().size();

        // Create the Instruction with an existing ID
        instruction.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restInstructionMockMvc.perform(post("/api/instructions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(instruction)))
            .andExpect(status().isBadRequest());

        // Validate the Instruction in the database
        List<Instruction> instructionList = instructionRepository.findAll();
        assertThat(instructionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllInstructions() throws Exception {
        // Initialize the database
        instructionRepository.save(instruction);

        // Get all the instructionList
        restInstructionMockMvc.perform(get("/api/instructions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(instruction.getId())))
            .andExpect(jsonPath("$.[*].scheduleItemId").value(hasItem(DEFAULT_SCHEDULE_ITEM_ID.intValue())))
            .andExpect(jsonPath("$.[*].videoURL").value(hasItem(DEFAULT_VIDEO_URL.toString())))
            .andExpect(jsonPath("$.[*].photoURLs").value(hasItem(DEFAULT_PHOTO_UR_LS.toString())))
            .andExpect(jsonPath("$.[*].instruction").value(hasItem(DEFAULT_INSTRUCTION.toString())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())));
    }
    
    @Test
    public void getInstruction() throws Exception {
        // Initialize the database
        instructionRepository.save(instruction);

        // Get the instruction
        restInstructionMockMvc.perform(get("/api/instructions/{id}", instruction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(instruction.getId()))
            .andExpect(jsonPath("$.scheduleItemId").value(DEFAULT_SCHEDULE_ITEM_ID.intValue()))
            .andExpect(jsonPath("$.videoURL").value(DEFAULT_VIDEO_URL.toString()))
            .andExpect(jsonPath("$.photoURLs").value(DEFAULT_PHOTO_UR_LS.toString()))
            .andExpect(jsonPath("$.instruction").value(DEFAULT_INSTRUCTION.toString()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()));
    }

    @Test
    public void getNonExistingInstruction() throws Exception {
        // Get the instruction
        restInstructionMockMvc.perform(get("/api/instructions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateInstruction() throws Exception {
        // Initialize the database
        instructionRepository.save(instruction);

        int databaseSizeBeforeUpdate = instructionRepository.findAll().size();

        // Update the instruction
        Instruction updatedInstruction = instructionRepository.findById(instruction.getId()).get();
        updatedInstruction
            .scheduleItemId(UPDATED_SCHEDULE_ITEM_ID)
            .videoURL(UPDATED_VIDEO_URL)
            .photoURLs(UPDATED_PHOTO_UR_LS)
            .instruction(UPDATED_INSTRUCTION)
            .title(UPDATED_TITLE);

        restInstructionMockMvc.perform(put("/api/instructions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedInstruction)))
            .andExpect(status().isOk());

        // Validate the Instruction in the database
        List<Instruction> instructionList = instructionRepository.findAll();
        assertThat(instructionList).hasSize(databaseSizeBeforeUpdate);
        Instruction testInstruction = instructionList.get(instructionList.size() - 1);
        assertThat(testInstruction.getScheduleItemId()).isEqualTo(UPDATED_SCHEDULE_ITEM_ID);
        assertThat(testInstruction.getVideoURL()).isEqualTo(UPDATED_VIDEO_URL);
        assertThat(testInstruction.getPhotoURLs()).isEqualTo(UPDATED_PHOTO_UR_LS);
        assertThat(testInstruction.getInstruction()).isEqualTo(UPDATED_INSTRUCTION);
        assertThat(testInstruction.getTitle()).isEqualTo(UPDATED_TITLE);
    }

    @Test
    public void updateNonExistingInstruction() throws Exception {
        int databaseSizeBeforeUpdate = instructionRepository.findAll().size();

        // Create the Instruction

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInstructionMockMvc.perform(put("/api/instructions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(instruction)))
            .andExpect(status().isBadRequest());

        // Validate the Instruction in the database
        List<Instruction> instructionList = instructionRepository.findAll();
        assertThat(instructionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteInstruction() throws Exception {
        // Initialize the database
        instructionRepository.save(instruction);

        int databaseSizeBeforeDelete = instructionRepository.findAll().size();

        // Get the instruction
        restInstructionMockMvc.perform(delete("/api/instructions/{id}", instruction.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Instruction> instructionList = instructionRepository.findAll();
        assertThat(instructionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Instruction.class);
        Instruction instruction1 = new Instruction();
        instruction1.setId("id1");
        Instruction instruction2 = new Instruction();
        instruction2.setId(instruction1.getId());
        assertThat(instruction1).isEqualTo(instruction2);
        instruction2.setId("id2");
        assertThat(instruction1).isNotEqualTo(instruction2);
        instruction1.setId(null);
        assertThat(instruction1).isNotEqualTo(instruction2);
    }
}
