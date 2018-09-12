package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplication2App;

import io.github.jhipster.application.domain.ImmunizationPlan;
import io.github.jhipster.application.repository.ImmunizationPlanRepository;
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
 * Test class for the ImmunizationPlanResource REST controller.
 *
 * @see ImmunizationPlanResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplication2App.class)
public class ImmunizationPlanResourceIntTest {

    private static final String DEFAULT_IMMUNIZATION_ITEMS = "AAAAAAAAAA";
    private static final String UPDATED_IMMUNIZATION_ITEMS = "BBBBBBBBBB";

    @Autowired
    private ImmunizationPlanRepository immunizationPlanRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restImmunizationPlanMockMvc;

    private ImmunizationPlan immunizationPlan;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ImmunizationPlanResource immunizationPlanResource = new ImmunizationPlanResource(immunizationPlanRepository);
        this.restImmunizationPlanMockMvc = MockMvcBuilders.standaloneSetup(immunizationPlanResource)
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
    public static ImmunizationPlan createEntity() {
        ImmunizationPlan immunizationPlan = new ImmunizationPlan()
            .immunizationItems(DEFAULT_IMMUNIZATION_ITEMS);
        return immunizationPlan;
    }

    @Before
    public void initTest() {
        immunizationPlanRepository.deleteAll();
        immunizationPlan = createEntity();
    }

    @Test
    public void createImmunizationPlan() throws Exception {
        int databaseSizeBeforeCreate = immunizationPlanRepository.findAll().size();

        // Create the ImmunizationPlan
        restImmunizationPlanMockMvc.perform(post("/api/immunization-plans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(immunizationPlan)))
            .andExpect(status().isCreated());

        // Validate the ImmunizationPlan in the database
        List<ImmunizationPlan> immunizationPlanList = immunizationPlanRepository.findAll();
        assertThat(immunizationPlanList).hasSize(databaseSizeBeforeCreate + 1);
        ImmunizationPlan testImmunizationPlan = immunizationPlanList.get(immunizationPlanList.size() - 1);
        assertThat(testImmunizationPlan.getImmunizationItems()).isEqualTo(DEFAULT_IMMUNIZATION_ITEMS);
    }

    @Test
    public void createImmunizationPlanWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = immunizationPlanRepository.findAll().size();

        // Create the ImmunizationPlan with an existing ID
        immunizationPlan.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restImmunizationPlanMockMvc.perform(post("/api/immunization-plans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(immunizationPlan)))
            .andExpect(status().isBadRequest());

        // Validate the ImmunizationPlan in the database
        List<ImmunizationPlan> immunizationPlanList = immunizationPlanRepository.findAll();
        assertThat(immunizationPlanList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllImmunizationPlans() throws Exception {
        // Initialize the database
        immunizationPlanRepository.save(immunizationPlan);

        // Get all the immunizationPlanList
        restImmunizationPlanMockMvc.perform(get("/api/immunization-plans?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(immunizationPlan.getId())))
            .andExpect(jsonPath("$.[*].immunizationItems").value(hasItem(DEFAULT_IMMUNIZATION_ITEMS.toString())));
    }
    
    @Test
    public void getImmunizationPlan() throws Exception {
        // Initialize the database
        immunizationPlanRepository.save(immunizationPlan);

        // Get the immunizationPlan
        restImmunizationPlanMockMvc.perform(get("/api/immunization-plans/{id}", immunizationPlan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(immunizationPlan.getId()))
            .andExpect(jsonPath("$.immunizationItems").value(DEFAULT_IMMUNIZATION_ITEMS.toString()));
    }

    @Test
    public void getNonExistingImmunizationPlan() throws Exception {
        // Get the immunizationPlan
        restImmunizationPlanMockMvc.perform(get("/api/immunization-plans/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateImmunizationPlan() throws Exception {
        // Initialize the database
        immunizationPlanRepository.save(immunizationPlan);

        int databaseSizeBeforeUpdate = immunizationPlanRepository.findAll().size();

        // Update the immunizationPlan
        ImmunizationPlan updatedImmunizationPlan = immunizationPlanRepository.findById(immunizationPlan.getId()).get();
        updatedImmunizationPlan
            .immunizationItems(UPDATED_IMMUNIZATION_ITEMS);

        restImmunizationPlanMockMvc.perform(put("/api/immunization-plans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedImmunizationPlan)))
            .andExpect(status().isOk());

        // Validate the ImmunizationPlan in the database
        List<ImmunizationPlan> immunizationPlanList = immunizationPlanRepository.findAll();
        assertThat(immunizationPlanList).hasSize(databaseSizeBeforeUpdate);
        ImmunizationPlan testImmunizationPlan = immunizationPlanList.get(immunizationPlanList.size() - 1);
        assertThat(testImmunizationPlan.getImmunizationItems()).isEqualTo(UPDATED_IMMUNIZATION_ITEMS);
    }

    @Test
    public void updateNonExistingImmunizationPlan() throws Exception {
        int databaseSizeBeforeUpdate = immunizationPlanRepository.findAll().size();

        // Create the ImmunizationPlan

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restImmunizationPlanMockMvc.perform(put("/api/immunization-plans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(immunizationPlan)))
            .andExpect(status().isBadRequest());

        // Validate the ImmunizationPlan in the database
        List<ImmunizationPlan> immunizationPlanList = immunizationPlanRepository.findAll();
        assertThat(immunizationPlanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteImmunizationPlan() throws Exception {
        // Initialize the database
        immunizationPlanRepository.save(immunizationPlan);

        int databaseSizeBeforeDelete = immunizationPlanRepository.findAll().size();

        // Get the immunizationPlan
        restImmunizationPlanMockMvc.perform(delete("/api/immunization-plans/{id}", immunizationPlan.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ImmunizationPlan> immunizationPlanList = immunizationPlanRepository.findAll();
        assertThat(immunizationPlanList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ImmunizationPlan.class);
        ImmunizationPlan immunizationPlan1 = new ImmunizationPlan();
        immunizationPlan1.setId("id1");
        ImmunizationPlan immunizationPlan2 = new ImmunizationPlan();
        immunizationPlan2.setId(immunizationPlan1.getId());
        assertThat(immunizationPlan1).isEqualTo(immunizationPlan2);
        immunizationPlan2.setId("id2");
        assertThat(immunizationPlan1).isNotEqualTo(immunizationPlan2);
        immunizationPlan1.setId(null);
        assertThat(immunizationPlan1).isNotEqualTo(immunizationPlan2);
    }
}
