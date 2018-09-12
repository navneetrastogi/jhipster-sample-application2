package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplication2App;

import io.github.jhipster.application.domain.IncidentRecord;
import io.github.jhipster.application.repository.IncidentRecordRepository;
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
 * Test class for the IncidentRecordResource REST controller.
 *
 * @see IncidentRecordResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplication2App.class)
public class IncidentRecordResourceIntTest {

    private static final String DEFAULT_NATURE_OF_INCIDENT = "AAAAAAAAAA";
    private static final String UPDATED_NATURE_OF_INCIDENT = "BBBBBBBBBB";

    private static final Boolean DEFAULT_FIRST_AID_PROVIDED = false;
    private static final Boolean UPDATED_FIRST_AID_PROVIDED = true;

    private static final String DEFAULT_FIRST_AID_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_AID_NOTES = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_CREATED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_ON = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_IS_ADMIN_ONLY = false;
    private static final Boolean UPDATED_IS_ADMIN_ONLY = true;

    private static final String DEFAULT_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_NOTES = "BBBBBBBBBB";

    private static final String DEFAULT_STUDENTS = "AAAAAAAAAA";
    private static final String UPDATED_STUDENTS = "BBBBBBBBBB";

    @Autowired
    private IncidentRecordRepository incidentRecordRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restIncidentRecordMockMvc;

    private IncidentRecord incidentRecord;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final IncidentRecordResource incidentRecordResource = new IncidentRecordResource(incidentRecordRepository);
        this.restIncidentRecordMockMvc = MockMvcBuilders.standaloneSetup(incidentRecordResource)
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
    public static IncidentRecord createEntity() {
        IncidentRecord incidentRecord = new IncidentRecord()
            .natureOfIncident(DEFAULT_NATURE_OF_INCIDENT)
            .firstAidProvided(DEFAULT_FIRST_AID_PROVIDED)
            .firstAidNotes(DEFAULT_FIRST_AID_NOTES)
            .date(DEFAULT_DATE)
            .createdOn(DEFAULT_CREATED_ON)
            .isAdminOnly(DEFAULT_IS_ADMIN_ONLY)
            .notes(DEFAULT_NOTES)
            .students(DEFAULT_STUDENTS);
        return incidentRecord;
    }

    @Before
    public void initTest() {
        incidentRecordRepository.deleteAll();
        incidentRecord = createEntity();
    }

    @Test
    public void createIncidentRecord() throws Exception {
        int databaseSizeBeforeCreate = incidentRecordRepository.findAll().size();

        // Create the IncidentRecord
        restIncidentRecordMockMvc.perform(post("/api/incident-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(incidentRecord)))
            .andExpect(status().isCreated());

        // Validate the IncidentRecord in the database
        List<IncidentRecord> incidentRecordList = incidentRecordRepository.findAll();
        assertThat(incidentRecordList).hasSize(databaseSizeBeforeCreate + 1);
        IncidentRecord testIncidentRecord = incidentRecordList.get(incidentRecordList.size() - 1);
        assertThat(testIncidentRecord.getNatureOfIncident()).isEqualTo(DEFAULT_NATURE_OF_INCIDENT);
        assertThat(testIncidentRecord.isFirstAidProvided()).isEqualTo(DEFAULT_FIRST_AID_PROVIDED);
        assertThat(testIncidentRecord.getFirstAidNotes()).isEqualTo(DEFAULT_FIRST_AID_NOTES);
        assertThat(testIncidentRecord.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testIncidentRecord.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testIncidentRecord.isIsAdminOnly()).isEqualTo(DEFAULT_IS_ADMIN_ONLY);
        assertThat(testIncidentRecord.getNotes()).isEqualTo(DEFAULT_NOTES);
        assertThat(testIncidentRecord.getStudents()).isEqualTo(DEFAULT_STUDENTS);
    }

    @Test
    public void createIncidentRecordWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = incidentRecordRepository.findAll().size();

        // Create the IncidentRecord with an existing ID
        incidentRecord.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restIncidentRecordMockMvc.perform(post("/api/incident-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(incidentRecord)))
            .andExpect(status().isBadRequest());

        // Validate the IncidentRecord in the database
        List<IncidentRecord> incidentRecordList = incidentRecordRepository.findAll();
        assertThat(incidentRecordList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllIncidentRecords() throws Exception {
        // Initialize the database
        incidentRecordRepository.save(incidentRecord);

        // Get all the incidentRecordList
        restIncidentRecordMockMvc.perform(get("/api/incident-records?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(incidentRecord.getId())))
            .andExpect(jsonPath("$.[*].natureOfIncident").value(hasItem(DEFAULT_NATURE_OF_INCIDENT.toString())))
            .andExpect(jsonPath("$.[*].firstAidProvided").value(hasItem(DEFAULT_FIRST_AID_PROVIDED.booleanValue())))
            .andExpect(jsonPath("$.[*].firstAidNotes").value(hasItem(DEFAULT_FIRST_AID_NOTES.toString())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].isAdminOnly").value(hasItem(DEFAULT_IS_ADMIN_ONLY.booleanValue())))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES.toString())))
            .andExpect(jsonPath("$.[*].students").value(hasItem(DEFAULT_STUDENTS.toString())));
    }
    
    @Test
    public void getIncidentRecord() throws Exception {
        // Initialize the database
        incidentRecordRepository.save(incidentRecord);

        // Get the incidentRecord
        restIncidentRecordMockMvc.perform(get("/api/incident-records/{id}", incidentRecord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(incidentRecord.getId()))
            .andExpect(jsonPath("$.natureOfIncident").value(DEFAULT_NATURE_OF_INCIDENT.toString()))
            .andExpect(jsonPath("$.firstAidProvided").value(DEFAULT_FIRST_AID_PROVIDED.booleanValue()))
            .andExpect(jsonPath("$.firstAidNotes").value(DEFAULT_FIRST_AID_NOTES.toString()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.isAdminOnly").value(DEFAULT_IS_ADMIN_ONLY.booleanValue()))
            .andExpect(jsonPath("$.notes").value(DEFAULT_NOTES.toString()))
            .andExpect(jsonPath("$.students").value(DEFAULT_STUDENTS.toString()));
    }

    @Test
    public void getNonExistingIncidentRecord() throws Exception {
        // Get the incidentRecord
        restIncidentRecordMockMvc.perform(get("/api/incident-records/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateIncidentRecord() throws Exception {
        // Initialize the database
        incidentRecordRepository.save(incidentRecord);

        int databaseSizeBeforeUpdate = incidentRecordRepository.findAll().size();

        // Update the incidentRecord
        IncidentRecord updatedIncidentRecord = incidentRecordRepository.findById(incidentRecord.getId()).get();
        updatedIncidentRecord
            .natureOfIncident(UPDATED_NATURE_OF_INCIDENT)
            .firstAidProvided(UPDATED_FIRST_AID_PROVIDED)
            .firstAidNotes(UPDATED_FIRST_AID_NOTES)
            .date(UPDATED_DATE)
            .createdOn(UPDATED_CREATED_ON)
            .isAdminOnly(UPDATED_IS_ADMIN_ONLY)
            .notes(UPDATED_NOTES)
            .students(UPDATED_STUDENTS);

        restIncidentRecordMockMvc.perform(put("/api/incident-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedIncidentRecord)))
            .andExpect(status().isOk());

        // Validate the IncidentRecord in the database
        List<IncidentRecord> incidentRecordList = incidentRecordRepository.findAll();
        assertThat(incidentRecordList).hasSize(databaseSizeBeforeUpdate);
        IncidentRecord testIncidentRecord = incidentRecordList.get(incidentRecordList.size() - 1);
        assertThat(testIncidentRecord.getNatureOfIncident()).isEqualTo(UPDATED_NATURE_OF_INCIDENT);
        assertThat(testIncidentRecord.isFirstAidProvided()).isEqualTo(UPDATED_FIRST_AID_PROVIDED);
        assertThat(testIncidentRecord.getFirstAidNotes()).isEqualTo(UPDATED_FIRST_AID_NOTES);
        assertThat(testIncidentRecord.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testIncidentRecord.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testIncidentRecord.isIsAdminOnly()).isEqualTo(UPDATED_IS_ADMIN_ONLY);
        assertThat(testIncidentRecord.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testIncidentRecord.getStudents()).isEqualTo(UPDATED_STUDENTS);
    }

    @Test
    public void updateNonExistingIncidentRecord() throws Exception {
        int databaseSizeBeforeUpdate = incidentRecordRepository.findAll().size();

        // Create the IncidentRecord

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIncidentRecordMockMvc.perform(put("/api/incident-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(incidentRecord)))
            .andExpect(status().isBadRequest());

        // Validate the IncidentRecord in the database
        List<IncidentRecord> incidentRecordList = incidentRecordRepository.findAll();
        assertThat(incidentRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteIncidentRecord() throws Exception {
        // Initialize the database
        incidentRecordRepository.save(incidentRecord);

        int databaseSizeBeforeDelete = incidentRecordRepository.findAll().size();

        // Get the incidentRecord
        restIncidentRecordMockMvc.perform(delete("/api/incident-records/{id}", incidentRecord.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<IncidentRecord> incidentRecordList = incidentRecordRepository.findAll();
        assertThat(incidentRecordList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(IncidentRecord.class);
        IncidentRecord incidentRecord1 = new IncidentRecord();
        incidentRecord1.setId("id1");
        IncidentRecord incidentRecord2 = new IncidentRecord();
        incidentRecord2.setId(incidentRecord1.getId());
        assertThat(incidentRecord1).isEqualTo(incidentRecord2);
        incidentRecord2.setId("id2");
        assertThat(incidentRecord1).isNotEqualTo(incidentRecord2);
        incidentRecord1.setId(null);
        assertThat(incidentRecord1).isNotEqualTo(incidentRecord2);
    }
}
