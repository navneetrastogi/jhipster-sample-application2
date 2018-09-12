package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplication2App;

import io.github.jhipster.application.domain.Incident;
import io.github.jhipster.application.repository.IncidentRepository;
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
 * Test class for the IncidentResource REST controller.
 *
 * @see IncidentResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplication2App.class)
public class IncidentResourceIntTest {

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

    private static final String DEFAULT_ROLE = "AAAAAAAAAA";
    private static final String UPDATED_ROLE = "BBBBBBBBBB";

    @Autowired
    private IncidentRepository incidentRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restIncidentMockMvc;

    private Incident incident;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final IncidentResource incidentResource = new IncidentResource(incidentRepository);
        this.restIncidentMockMvc = MockMvcBuilders.standaloneSetup(incidentResource)
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
    public static Incident createEntity() {
        Incident incident = new Incident()
            .natureOfIncident(DEFAULT_NATURE_OF_INCIDENT)
            .firstAidProvided(DEFAULT_FIRST_AID_PROVIDED)
            .firstAidNotes(DEFAULT_FIRST_AID_NOTES)
            .date(DEFAULT_DATE)
            .createdOn(DEFAULT_CREATED_ON)
            .isAdminOnly(DEFAULT_IS_ADMIN_ONLY)
            .notes(DEFAULT_NOTES)
            .role(DEFAULT_ROLE);
        return incident;
    }

    @Before
    public void initTest() {
        incidentRepository.deleteAll();
        incident = createEntity();
    }

    @Test
    public void createIncident() throws Exception {
        int databaseSizeBeforeCreate = incidentRepository.findAll().size();

        // Create the Incident
        restIncidentMockMvc.perform(post("/api/incidents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(incident)))
            .andExpect(status().isCreated());

        // Validate the Incident in the database
        List<Incident> incidentList = incidentRepository.findAll();
        assertThat(incidentList).hasSize(databaseSizeBeforeCreate + 1);
        Incident testIncident = incidentList.get(incidentList.size() - 1);
        assertThat(testIncident.getNatureOfIncident()).isEqualTo(DEFAULT_NATURE_OF_INCIDENT);
        assertThat(testIncident.isFirstAidProvided()).isEqualTo(DEFAULT_FIRST_AID_PROVIDED);
        assertThat(testIncident.getFirstAidNotes()).isEqualTo(DEFAULT_FIRST_AID_NOTES);
        assertThat(testIncident.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testIncident.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testIncident.isIsAdminOnly()).isEqualTo(DEFAULT_IS_ADMIN_ONLY);
        assertThat(testIncident.getNotes()).isEqualTo(DEFAULT_NOTES);
        assertThat(testIncident.getRole()).isEqualTo(DEFAULT_ROLE);
    }

    @Test
    public void createIncidentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = incidentRepository.findAll().size();

        // Create the Incident with an existing ID
        incident.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restIncidentMockMvc.perform(post("/api/incidents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(incident)))
            .andExpect(status().isBadRequest());

        // Validate the Incident in the database
        List<Incident> incidentList = incidentRepository.findAll();
        assertThat(incidentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllIncidents() throws Exception {
        // Initialize the database
        incidentRepository.save(incident);

        // Get all the incidentList
        restIncidentMockMvc.perform(get("/api/incidents?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(incident.getId())))
            .andExpect(jsonPath("$.[*].natureOfIncident").value(hasItem(DEFAULT_NATURE_OF_INCIDENT.toString())))
            .andExpect(jsonPath("$.[*].firstAidProvided").value(hasItem(DEFAULT_FIRST_AID_PROVIDED.booleanValue())))
            .andExpect(jsonPath("$.[*].firstAidNotes").value(hasItem(DEFAULT_FIRST_AID_NOTES.toString())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].isAdminOnly").value(hasItem(DEFAULT_IS_ADMIN_ONLY.booleanValue())))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES.toString())))
            .andExpect(jsonPath("$.[*].role").value(hasItem(DEFAULT_ROLE.toString())));
    }
    
    @Test
    public void getIncident() throws Exception {
        // Initialize the database
        incidentRepository.save(incident);

        // Get the incident
        restIncidentMockMvc.perform(get("/api/incidents/{id}", incident.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(incident.getId()))
            .andExpect(jsonPath("$.natureOfIncident").value(DEFAULT_NATURE_OF_INCIDENT.toString()))
            .andExpect(jsonPath("$.firstAidProvided").value(DEFAULT_FIRST_AID_PROVIDED.booleanValue()))
            .andExpect(jsonPath("$.firstAidNotes").value(DEFAULT_FIRST_AID_NOTES.toString()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.isAdminOnly").value(DEFAULT_IS_ADMIN_ONLY.booleanValue()))
            .andExpect(jsonPath("$.notes").value(DEFAULT_NOTES.toString()))
            .andExpect(jsonPath("$.role").value(DEFAULT_ROLE.toString()));
    }

    @Test
    public void getNonExistingIncident() throws Exception {
        // Get the incident
        restIncidentMockMvc.perform(get("/api/incidents/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateIncident() throws Exception {
        // Initialize the database
        incidentRepository.save(incident);

        int databaseSizeBeforeUpdate = incidentRepository.findAll().size();

        // Update the incident
        Incident updatedIncident = incidentRepository.findById(incident.getId()).get();
        updatedIncident
            .natureOfIncident(UPDATED_NATURE_OF_INCIDENT)
            .firstAidProvided(UPDATED_FIRST_AID_PROVIDED)
            .firstAidNotes(UPDATED_FIRST_AID_NOTES)
            .date(UPDATED_DATE)
            .createdOn(UPDATED_CREATED_ON)
            .isAdminOnly(UPDATED_IS_ADMIN_ONLY)
            .notes(UPDATED_NOTES)
            .role(UPDATED_ROLE);

        restIncidentMockMvc.perform(put("/api/incidents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedIncident)))
            .andExpect(status().isOk());

        // Validate the Incident in the database
        List<Incident> incidentList = incidentRepository.findAll();
        assertThat(incidentList).hasSize(databaseSizeBeforeUpdate);
        Incident testIncident = incidentList.get(incidentList.size() - 1);
        assertThat(testIncident.getNatureOfIncident()).isEqualTo(UPDATED_NATURE_OF_INCIDENT);
        assertThat(testIncident.isFirstAidProvided()).isEqualTo(UPDATED_FIRST_AID_PROVIDED);
        assertThat(testIncident.getFirstAidNotes()).isEqualTo(UPDATED_FIRST_AID_NOTES);
        assertThat(testIncident.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testIncident.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testIncident.isIsAdminOnly()).isEqualTo(UPDATED_IS_ADMIN_ONLY);
        assertThat(testIncident.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testIncident.getRole()).isEqualTo(UPDATED_ROLE);
    }

    @Test
    public void updateNonExistingIncident() throws Exception {
        int databaseSizeBeforeUpdate = incidentRepository.findAll().size();

        // Create the Incident

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIncidentMockMvc.perform(put("/api/incidents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(incident)))
            .andExpect(status().isBadRequest());

        // Validate the Incident in the database
        List<Incident> incidentList = incidentRepository.findAll();
        assertThat(incidentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteIncident() throws Exception {
        // Initialize the database
        incidentRepository.save(incident);

        int databaseSizeBeforeDelete = incidentRepository.findAll().size();

        // Get the incident
        restIncidentMockMvc.perform(delete("/api/incidents/{id}", incident.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Incident> incidentList = incidentRepository.findAll();
        assertThat(incidentList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Incident.class);
        Incident incident1 = new Incident();
        incident1.setId("id1");
        Incident incident2 = new Incident();
        incident2.setId(incident1.getId());
        assertThat(incident1).isEqualTo(incident2);
        incident2.setId("id2");
        assertThat(incident1).isNotEqualTo(incident2);
        incident1.setId(null);
        assertThat(incident1).isNotEqualTo(incident2);
    }
}
