package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplication2App;

import io.github.jhipster.application.domain.ActivityType;
import io.github.jhipster.application.repository.ActivityTypeRepository;
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
 * Test class for the ActivityTypeResource REST controller.
 *
 * @see ActivityTypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplication2App.class)
public class ActivityTypeResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY = "BBBBBBBBBB";

    @Autowired
    private ActivityTypeRepository activityTypeRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restActivityTypeMockMvc;

    private ActivityType activityType;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ActivityTypeResource activityTypeResource = new ActivityTypeResource(activityTypeRepository);
        this.restActivityTypeMockMvc = MockMvcBuilders.standaloneSetup(activityTypeResource)
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
    public static ActivityType createEntity() {
        ActivityType activityType = new ActivityType()
            .name(DEFAULT_NAME)
            .category(DEFAULT_CATEGORY);
        return activityType;
    }

    @Before
    public void initTest() {
        activityTypeRepository.deleteAll();
        activityType = createEntity();
    }

    @Test
    public void createActivityType() throws Exception {
        int databaseSizeBeforeCreate = activityTypeRepository.findAll().size();

        // Create the ActivityType
        restActivityTypeMockMvc.perform(post("/api/activity-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(activityType)))
            .andExpect(status().isCreated());

        // Validate the ActivityType in the database
        List<ActivityType> activityTypeList = activityTypeRepository.findAll();
        assertThat(activityTypeList).hasSize(databaseSizeBeforeCreate + 1);
        ActivityType testActivityType = activityTypeList.get(activityTypeList.size() - 1);
        assertThat(testActivityType.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testActivityType.getCategory()).isEqualTo(DEFAULT_CATEGORY);
    }

    @Test
    public void createActivityTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = activityTypeRepository.findAll().size();

        // Create the ActivityType with an existing ID
        activityType.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restActivityTypeMockMvc.perform(post("/api/activity-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(activityType)))
            .andExpect(status().isBadRequest());

        // Validate the ActivityType in the database
        List<ActivityType> activityTypeList = activityTypeRepository.findAll();
        assertThat(activityTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllActivityTypes() throws Exception {
        // Initialize the database
        activityTypeRepository.save(activityType);

        // Get all the activityTypeList
        restActivityTypeMockMvc.perform(get("/api/activity-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(activityType.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].category").value(hasItem(DEFAULT_CATEGORY.toString())));
    }
    
    @Test
    public void getActivityType() throws Exception {
        // Initialize the database
        activityTypeRepository.save(activityType);

        // Get the activityType
        restActivityTypeMockMvc.perform(get("/api/activity-types/{id}", activityType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(activityType.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.category").value(DEFAULT_CATEGORY.toString()));
    }

    @Test
    public void getNonExistingActivityType() throws Exception {
        // Get the activityType
        restActivityTypeMockMvc.perform(get("/api/activity-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateActivityType() throws Exception {
        // Initialize the database
        activityTypeRepository.save(activityType);

        int databaseSizeBeforeUpdate = activityTypeRepository.findAll().size();

        // Update the activityType
        ActivityType updatedActivityType = activityTypeRepository.findById(activityType.getId()).get();
        updatedActivityType
            .name(UPDATED_NAME)
            .category(UPDATED_CATEGORY);

        restActivityTypeMockMvc.perform(put("/api/activity-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedActivityType)))
            .andExpect(status().isOk());

        // Validate the ActivityType in the database
        List<ActivityType> activityTypeList = activityTypeRepository.findAll();
        assertThat(activityTypeList).hasSize(databaseSizeBeforeUpdate);
        ActivityType testActivityType = activityTypeList.get(activityTypeList.size() - 1);
        assertThat(testActivityType.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testActivityType.getCategory()).isEqualTo(UPDATED_CATEGORY);
    }

    @Test
    public void updateNonExistingActivityType() throws Exception {
        int databaseSizeBeforeUpdate = activityTypeRepository.findAll().size();

        // Create the ActivityType

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restActivityTypeMockMvc.perform(put("/api/activity-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(activityType)))
            .andExpect(status().isBadRequest());

        // Validate the ActivityType in the database
        List<ActivityType> activityTypeList = activityTypeRepository.findAll();
        assertThat(activityTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteActivityType() throws Exception {
        // Initialize the database
        activityTypeRepository.save(activityType);

        int databaseSizeBeforeDelete = activityTypeRepository.findAll().size();

        // Get the activityType
        restActivityTypeMockMvc.perform(delete("/api/activity-types/{id}", activityType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ActivityType> activityTypeList = activityTypeRepository.findAll();
        assertThat(activityTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ActivityType.class);
        ActivityType activityType1 = new ActivityType();
        activityType1.setId("id1");
        ActivityType activityType2 = new ActivityType();
        activityType2.setId(activityType1.getId());
        assertThat(activityType1).isEqualTo(activityType2);
        activityType2.setId("id2");
        assertThat(activityType1).isNotEqualTo(activityType2);
        activityType1.setId(null);
        assertThat(activityType1).isNotEqualTo(activityType2);
    }
}
