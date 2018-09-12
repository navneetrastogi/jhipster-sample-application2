package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplication2App;

import io.github.jhipster.application.domain.EventPhotos;
import io.github.jhipster.application.repository.EventPhotosRepository;
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
 * Test class for the EventPhotosResource REST controller.
 *
 * @see EventPhotosResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplication2App.class)
public class EventPhotosResourceIntTest {

    private static final Long DEFAULT_EVENTID = 1L;
    private static final Long UPDATED_EVENTID = 2L;

    private static final String DEFAULT_IMAGE_URLS = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_URLS = "BBBBBBBBBB";

    private static final String DEFAULT_LIKES = "AAAAAAAAAA";
    private static final String UPDATED_LIKES = "BBBBBBBBBB";

    private static final String DEFAULT_SEEN = "AAAAAAAAAA";
    private static final String UPDATED_SEEN = "BBBBBBBBBB";

    @Autowired
    private EventPhotosRepository eventPhotosRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restEventPhotosMockMvc;

    private EventPhotos eventPhotos;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EventPhotosResource eventPhotosResource = new EventPhotosResource(eventPhotosRepository);
        this.restEventPhotosMockMvc = MockMvcBuilders.standaloneSetup(eventPhotosResource)
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
    public static EventPhotos createEntity() {
        EventPhotos eventPhotos = new EventPhotos()
            .eventid(DEFAULT_EVENTID)
            .imageUrls(DEFAULT_IMAGE_URLS)
            .likes(DEFAULT_LIKES)
            .seen(DEFAULT_SEEN);
        return eventPhotos;
    }

    @Before
    public void initTest() {
        eventPhotosRepository.deleteAll();
        eventPhotos = createEntity();
    }

    @Test
    public void createEventPhotos() throws Exception {
        int databaseSizeBeforeCreate = eventPhotosRepository.findAll().size();

        // Create the EventPhotos
        restEventPhotosMockMvc.perform(post("/api/event-photos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eventPhotos)))
            .andExpect(status().isCreated());

        // Validate the EventPhotos in the database
        List<EventPhotos> eventPhotosList = eventPhotosRepository.findAll();
        assertThat(eventPhotosList).hasSize(databaseSizeBeforeCreate + 1);
        EventPhotos testEventPhotos = eventPhotosList.get(eventPhotosList.size() - 1);
        assertThat(testEventPhotos.getEventid()).isEqualTo(DEFAULT_EVENTID);
        assertThat(testEventPhotos.getImageUrls()).isEqualTo(DEFAULT_IMAGE_URLS);
        assertThat(testEventPhotos.getLikes()).isEqualTo(DEFAULT_LIKES);
        assertThat(testEventPhotos.getSeen()).isEqualTo(DEFAULT_SEEN);
    }

    @Test
    public void createEventPhotosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = eventPhotosRepository.findAll().size();

        // Create the EventPhotos with an existing ID
        eventPhotos.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restEventPhotosMockMvc.perform(post("/api/event-photos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eventPhotos)))
            .andExpect(status().isBadRequest());

        // Validate the EventPhotos in the database
        List<EventPhotos> eventPhotosList = eventPhotosRepository.findAll();
        assertThat(eventPhotosList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllEventPhotos() throws Exception {
        // Initialize the database
        eventPhotosRepository.save(eventPhotos);

        // Get all the eventPhotosList
        restEventPhotosMockMvc.perform(get("/api/event-photos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(eventPhotos.getId())))
            .andExpect(jsonPath("$.[*].eventid").value(hasItem(DEFAULT_EVENTID.intValue())))
            .andExpect(jsonPath("$.[*].imageUrls").value(hasItem(DEFAULT_IMAGE_URLS.toString())))
            .andExpect(jsonPath("$.[*].likes").value(hasItem(DEFAULT_LIKES.toString())))
            .andExpect(jsonPath("$.[*].seen").value(hasItem(DEFAULT_SEEN.toString())));
    }
    
    @Test
    public void getEventPhotos() throws Exception {
        // Initialize the database
        eventPhotosRepository.save(eventPhotos);

        // Get the eventPhotos
        restEventPhotosMockMvc.perform(get("/api/event-photos/{id}", eventPhotos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(eventPhotos.getId()))
            .andExpect(jsonPath("$.eventid").value(DEFAULT_EVENTID.intValue()))
            .andExpect(jsonPath("$.imageUrls").value(DEFAULT_IMAGE_URLS.toString()))
            .andExpect(jsonPath("$.likes").value(DEFAULT_LIKES.toString()))
            .andExpect(jsonPath("$.seen").value(DEFAULT_SEEN.toString()));
    }

    @Test
    public void getNonExistingEventPhotos() throws Exception {
        // Get the eventPhotos
        restEventPhotosMockMvc.perform(get("/api/event-photos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateEventPhotos() throws Exception {
        // Initialize the database
        eventPhotosRepository.save(eventPhotos);

        int databaseSizeBeforeUpdate = eventPhotosRepository.findAll().size();

        // Update the eventPhotos
        EventPhotos updatedEventPhotos = eventPhotosRepository.findById(eventPhotos.getId()).get();
        updatedEventPhotos
            .eventid(UPDATED_EVENTID)
            .imageUrls(UPDATED_IMAGE_URLS)
            .likes(UPDATED_LIKES)
            .seen(UPDATED_SEEN);

        restEventPhotosMockMvc.perform(put("/api/event-photos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedEventPhotos)))
            .andExpect(status().isOk());

        // Validate the EventPhotos in the database
        List<EventPhotos> eventPhotosList = eventPhotosRepository.findAll();
        assertThat(eventPhotosList).hasSize(databaseSizeBeforeUpdate);
        EventPhotos testEventPhotos = eventPhotosList.get(eventPhotosList.size() - 1);
        assertThat(testEventPhotos.getEventid()).isEqualTo(UPDATED_EVENTID);
        assertThat(testEventPhotos.getImageUrls()).isEqualTo(UPDATED_IMAGE_URLS);
        assertThat(testEventPhotos.getLikes()).isEqualTo(UPDATED_LIKES);
        assertThat(testEventPhotos.getSeen()).isEqualTo(UPDATED_SEEN);
    }

    @Test
    public void updateNonExistingEventPhotos() throws Exception {
        int databaseSizeBeforeUpdate = eventPhotosRepository.findAll().size();

        // Create the EventPhotos

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEventPhotosMockMvc.perform(put("/api/event-photos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eventPhotos)))
            .andExpect(status().isBadRequest());

        // Validate the EventPhotos in the database
        List<EventPhotos> eventPhotosList = eventPhotosRepository.findAll();
        assertThat(eventPhotosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteEventPhotos() throws Exception {
        // Initialize the database
        eventPhotosRepository.save(eventPhotos);

        int databaseSizeBeforeDelete = eventPhotosRepository.findAll().size();

        // Get the eventPhotos
        restEventPhotosMockMvc.perform(delete("/api/event-photos/{id}", eventPhotos.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<EventPhotos> eventPhotosList = eventPhotosRepository.findAll();
        assertThat(eventPhotosList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EventPhotos.class);
        EventPhotos eventPhotos1 = new EventPhotos();
        eventPhotos1.setId("id1");
        EventPhotos eventPhotos2 = new EventPhotos();
        eventPhotos2.setId(eventPhotos1.getId());
        assertThat(eventPhotos1).isEqualTo(eventPhotos2);
        eventPhotos2.setId("id2");
        assertThat(eventPhotos1).isNotEqualTo(eventPhotos2);
        eventPhotos1.setId(null);
        assertThat(eventPhotos1).isNotEqualTo(eventPhotos2);
    }
}
