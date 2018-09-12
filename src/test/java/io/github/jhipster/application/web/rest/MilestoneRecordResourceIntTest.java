package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplication2App;

import io.github.jhipster.application.domain.MilestoneRecord;
import io.github.jhipster.application.repository.MilestoneRecordRepository;
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

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the MilestoneRecordResource REST controller.
 *
 * @see MilestoneRecordResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplication2App.class)
public class MilestoneRecordResourceIntTest {

    private static final LocalDate DEFAULT_CREATED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_ON = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_NOTES = "BBBBBBBBBB";

    @Autowired
    private MilestoneRecordRepository milestoneRecordRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restMilestoneRecordMockMvc;

    private MilestoneRecord milestoneRecord;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MilestoneRecordResource milestoneRecordResource = new MilestoneRecordResource(milestoneRecordRepository);
        this.restMilestoneRecordMockMvc = MockMvcBuilders.standaloneSetup(milestoneRecordResource)
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
    public static MilestoneRecord createEntity() {
        MilestoneRecord milestoneRecord = new MilestoneRecord()
            .createdOn(DEFAULT_CREATED_ON)
            .notes(DEFAULT_NOTES);
        return milestoneRecord;
    }

    @Before
    public void initTest() {
        milestoneRecordRepository.deleteAll();
        milestoneRecord = createEntity();
    }

    @Test
    public void createMilestoneRecord() throws Exception {
        int databaseSizeBeforeCreate = milestoneRecordRepository.findAll().size();

        // Create the MilestoneRecord
        restMilestoneRecordMockMvc.perform(post("/api/milestone-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(milestoneRecord)))
            .andExpect(status().isCreated());

        // Validate the MilestoneRecord in the database
        List<MilestoneRecord> milestoneRecordList = milestoneRecordRepository.findAll();
        assertThat(milestoneRecordList).hasSize(databaseSizeBeforeCreate + 1);
        MilestoneRecord testMilestoneRecord = milestoneRecordList.get(milestoneRecordList.size() - 1);
        assertThat(testMilestoneRecord.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testMilestoneRecord.getNotes()).isEqualTo(DEFAULT_NOTES);
    }

    @Test
    public void createMilestoneRecordWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = milestoneRecordRepository.findAll().size();

        // Create the MilestoneRecord with an existing ID
        milestoneRecord.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restMilestoneRecordMockMvc.perform(post("/api/milestone-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(milestoneRecord)))
            .andExpect(status().isBadRequest());

        // Validate the MilestoneRecord in the database
        List<MilestoneRecord> milestoneRecordList = milestoneRecordRepository.findAll();
        assertThat(milestoneRecordList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllMilestoneRecords() throws Exception {
        // Initialize the database
        milestoneRecordRepository.save(milestoneRecord);

        // Get all the milestoneRecordList
        restMilestoneRecordMockMvc.perform(get("/api/milestone-records?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(milestoneRecord.getId())))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES.toString())));
    }
    
    @Test
    public void getMilestoneRecord() throws Exception {
        // Initialize the database
        milestoneRecordRepository.save(milestoneRecord);

        // Get the milestoneRecord
        restMilestoneRecordMockMvc.perform(get("/api/milestone-records/{id}", milestoneRecord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(milestoneRecord.getId()))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.notes").value(DEFAULT_NOTES.toString()));
    }

    @Test
    public void getNonExistingMilestoneRecord() throws Exception {
        // Get the milestoneRecord
        restMilestoneRecordMockMvc.perform(get("/api/milestone-records/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateMilestoneRecord() throws Exception {
        // Initialize the database
        milestoneRecordRepository.save(milestoneRecord);

        int databaseSizeBeforeUpdate = milestoneRecordRepository.findAll().size();

        // Update the milestoneRecord
        MilestoneRecord updatedMilestoneRecord = milestoneRecordRepository.findById(milestoneRecord.getId()).get();
        updatedMilestoneRecord
            .createdOn(UPDATED_CREATED_ON)
            .notes(UPDATED_NOTES);

        restMilestoneRecordMockMvc.perform(put("/api/milestone-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMilestoneRecord)))
            .andExpect(status().isOk());

        // Validate the MilestoneRecord in the database
        List<MilestoneRecord> milestoneRecordList = milestoneRecordRepository.findAll();
        assertThat(milestoneRecordList).hasSize(databaseSizeBeforeUpdate);
        MilestoneRecord testMilestoneRecord = milestoneRecordList.get(milestoneRecordList.size() - 1);
        assertThat(testMilestoneRecord.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testMilestoneRecord.getNotes()).isEqualTo(UPDATED_NOTES);
    }

    @Test
    public void updateNonExistingMilestoneRecord() throws Exception {
        int databaseSizeBeforeUpdate = milestoneRecordRepository.findAll().size();

        // Create the MilestoneRecord

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMilestoneRecordMockMvc.perform(put("/api/milestone-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(milestoneRecord)))
            .andExpect(status().isBadRequest());

        // Validate the MilestoneRecord in the database
        List<MilestoneRecord> milestoneRecordList = milestoneRecordRepository.findAll();
        assertThat(milestoneRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteMilestoneRecord() throws Exception {
        // Initialize the database
        milestoneRecordRepository.save(milestoneRecord);

        int databaseSizeBeforeDelete = milestoneRecordRepository.findAll().size();

        // Get the milestoneRecord
        restMilestoneRecordMockMvc.perform(delete("/api/milestone-records/{id}", milestoneRecord.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MilestoneRecord> milestoneRecordList = milestoneRecordRepository.findAll();
        assertThat(milestoneRecordList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MilestoneRecord.class);
        MilestoneRecord milestoneRecord1 = new MilestoneRecord();
        milestoneRecord1.setId("id1");
        MilestoneRecord milestoneRecord2 = new MilestoneRecord();
        milestoneRecord2.setId(milestoneRecord1.getId());
        assertThat(milestoneRecord1).isEqualTo(milestoneRecord2);
        milestoneRecord2.setId("id2");
        assertThat(milestoneRecord1).isNotEqualTo(milestoneRecord2);
        milestoneRecord1.setId(null);
        assertThat(milestoneRecord1).isNotEqualTo(milestoneRecord2);
    }
}
