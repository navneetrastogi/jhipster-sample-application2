package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplication2App;

import io.github.jhipster.application.domain.ImmunizationItems;
import io.github.jhipster.application.repository.ImmunizationItemsRepository;
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
 * Test class for the ImmunizationItemsResource REST controller.
 *
 * @see ImmunizationItemsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplication2App.class)
public class ImmunizationItemsResourceIntTest {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_START_AGE = 1;
    private static final Integer UPDATED_START_AGE = 2;

    private static final Integer DEFAULT_END_AGE = 1;
    private static final Integer UPDATED_END_AGE = 2;

    @Autowired
    private ImmunizationItemsRepository immunizationItemsRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restImmunizationItemsMockMvc;

    private ImmunizationItems immunizationItems;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ImmunizationItemsResource immunizationItemsResource = new ImmunizationItemsResource(immunizationItemsRepository);
        this.restImmunizationItemsMockMvc = MockMvcBuilders.standaloneSetup(immunizationItemsResource)
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
    public static ImmunizationItems createEntity() {
        ImmunizationItems immunizationItems = new ImmunizationItems()
            .title(DEFAULT_TITLE)
            .description(DEFAULT_DESCRIPTION)
            .startAge(DEFAULT_START_AGE)
            .endAge(DEFAULT_END_AGE);
        return immunizationItems;
    }

    @Before
    public void initTest() {
        immunizationItemsRepository.deleteAll();
        immunizationItems = createEntity();
    }

    @Test
    public void createImmunizationItems() throws Exception {
        int databaseSizeBeforeCreate = immunizationItemsRepository.findAll().size();

        // Create the ImmunizationItems
        restImmunizationItemsMockMvc.perform(post("/api/immunization-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(immunizationItems)))
            .andExpect(status().isCreated());

        // Validate the ImmunizationItems in the database
        List<ImmunizationItems> immunizationItemsList = immunizationItemsRepository.findAll();
        assertThat(immunizationItemsList).hasSize(databaseSizeBeforeCreate + 1);
        ImmunizationItems testImmunizationItems = immunizationItemsList.get(immunizationItemsList.size() - 1);
        assertThat(testImmunizationItems.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testImmunizationItems.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testImmunizationItems.getStartAge()).isEqualTo(DEFAULT_START_AGE);
        assertThat(testImmunizationItems.getEndAge()).isEqualTo(DEFAULT_END_AGE);
    }

    @Test
    public void createImmunizationItemsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = immunizationItemsRepository.findAll().size();

        // Create the ImmunizationItems with an existing ID
        immunizationItems.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restImmunizationItemsMockMvc.perform(post("/api/immunization-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(immunizationItems)))
            .andExpect(status().isBadRequest());

        // Validate the ImmunizationItems in the database
        List<ImmunizationItems> immunizationItemsList = immunizationItemsRepository.findAll();
        assertThat(immunizationItemsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllImmunizationItems() throws Exception {
        // Initialize the database
        immunizationItemsRepository.save(immunizationItems);

        // Get all the immunizationItemsList
        restImmunizationItemsMockMvc.perform(get("/api/immunization-items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(immunizationItems.getId())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].startAge").value(hasItem(DEFAULT_START_AGE)))
            .andExpect(jsonPath("$.[*].endAge").value(hasItem(DEFAULT_END_AGE)));
    }
    
    @Test
    public void getImmunizationItems() throws Exception {
        // Initialize the database
        immunizationItemsRepository.save(immunizationItems);

        // Get the immunizationItems
        restImmunizationItemsMockMvc.perform(get("/api/immunization-items/{id}", immunizationItems.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(immunizationItems.getId()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.startAge").value(DEFAULT_START_AGE))
            .andExpect(jsonPath("$.endAge").value(DEFAULT_END_AGE));
    }

    @Test
    public void getNonExistingImmunizationItems() throws Exception {
        // Get the immunizationItems
        restImmunizationItemsMockMvc.perform(get("/api/immunization-items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateImmunizationItems() throws Exception {
        // Initialize the database
        immunizationItemsRepository.save(immunizationItems);

        int databaseSizeBeforeUpdate = immunizationItemsRepository.findAll().size();

        // Update the immunizationItems
        ImmunizationItems updatedImmunizationItems = immunizationItemsRepository.findById(immunizationItems.getId()).get();
        updatedImmunizationItems
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .startAge(UPDATED_START_AGE)
            .endAge(UPDATED_END_AGE);

        restImmunizationItemsMockMvc.perform(put("/api/immunization-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedImmunizationItems)))
            .andExpect(status().isOk());

        // Validate the ImmunizationItems in the database
        List<ImmunizationItems> immunizationItemsList = immunizationItemsRepository.findAll();
        assertThat(immunizationItemsList).hasSize(databaseSizeBeforeUpdate);
        ImmunizationItems testImmunizationItems = immunizationItemsList.get(immunizationItemsList.size() - 1);
        assertThat(testImmunizationItems.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testImmunizationItems.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testImmunizationItems.getStartAge()).isEqualTo(UPDATED_START_AGE);
        assertThat(testImmunizationItems.getEndAge()).isEqualTo(UPDATED_END_AGE);
    }

    @Test
    public void updateNonExistingImmunizationItems() throws Exception {
        int databaseSizeBeforeUpdate = immunizationItemsRepository.findAll().size();

        // Create the ImmunizationItems

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restImmunizationItemsMockMvc.perform(put("/api/immunization-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(immunizationItems)))
            .andExpect(status().isBadRequest());

        // Validate the ImmunizationItems in the database
        List<ImmunizationItems> immunizationItemsList = immunizationItemsRepository.findAll();
        assertThat(immunizationItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteImmunizationItems() throws Exception {
        // Initialize the database
        immunizationItemsRepository.save(immunizationItems);

        int databaseSizeBeforeDelete = immunizationItemsRepository.findAll().size();

        // Get the immunizationItems
        restImmunizationItemsMockMvc.perform(delete("/api/immunization-items/{id}", immunizationItems.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ImmunizationItems> immunizationItemsList = immunizationItemsRepository.findAll();
        assertThat(immunizationItemsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ImmunizationItems.class);
        ImmunizationItems immunizationItems1 = new ImmunizationItems();
        immunizationItems1.setId("id1");
        ImmunizationItems immunizationItems2 = new ImmunizationItems();
        immunizationItems2.setId(immunizationItems1.getId());
        assertThat(immunizationItems1).isEqualTo(immunizationItems2);
        immunizationItems2.setId("id2");
        assertThat(immunizationItems1).isNotEqualTo(immunizationItems2);
        immunizationItems1.setId(null);
        assertThat(immunizationItems1).isNotEqualTo(immunizationItems2);
    }
}
