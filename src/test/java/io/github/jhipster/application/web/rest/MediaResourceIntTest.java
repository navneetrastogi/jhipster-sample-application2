package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplication2App;

import io.github.jhipster.application.domain.Media;
import io.github.jhipster.application.repository.MediaRepository;
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

import io.github.jhipster.application.domain.enumeration.IllumineMediaType;
/**
 * Test class for the MediaResource REST controller.
 *
 * @see MediaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplication2App.class)
public class MediaResourceIntTest {

    private static final IllumineMediaType DEFAULT_MEDIA_TYPE = IllumineMediaType.PHOTO;
    private static final IllumineMediaType UPDATED_MEDIA_TYPE = IllumineMediaType.VIDEO;

    private static final LocalDate DEFAULT_CREATED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_ON = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_FILE_URL = "AAAAAAAAAA";
    private static final String UPDATED_FILE_URL = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restMediaMockMvc;

    private Media media;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MediaResource mediaResource = new MediaResource(mediaRepository);
        this.restMediaMockMvc = MockMvcBuilders.standaloneSetup(mediaResource)
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
    public static Media createEntity() {
        Media media = new Media()
            .mediaType(DEFAULT_MEDIA_TYPE)
            .createdOn(DEFAULT_CREATED_ON)
            .fileUrl(DEFAULT_FILE_URL)
            .title(DEFAULT_TITLE);
        return media;
    }

    @Before
    public void initTest() {
        mediaRepository.deleteAll();
        media = createEntity();
    }

    @Test
    public void createMedia() throws Exception {
        int databaseSizeBeforeCreate = mediaRepository.findAll().size();

        // Create the Media
        restMediaMockMvc.perform(post("/api/media")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(media)))
            .andExpect(status().isCreated());

        // Validate the Media in the database
        List<Media> mediaList = mediaRepository.findAll();
        assertThat(mediaList).hasSize(databaseSizeBeforeCreate + 1);
        Media testMedia = mediaList.get(mediaList.size() - 1);
        assertThat(testMedia.getMediaType()).isEqualTo(DEFAULT_MEDIA_TYPE);
        assertThat(testMedia.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testMedia.getFileUrl()).isEqualTo(DEFAULT_FILE_URL);
        assertThat(testMedia.getTitle()).isEqualTo(DEFAULT_TITLE);
    }

    @Test
    public void createMediaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mediaRepository.findAll().size();

        // Create the Media with an existing ID
        media.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restMediaMockMvc.perform(post("/api/media")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(media)))
            .andExpect(status().isBadRequest());

        // Validate the Media in the database
        List<Media> mediaList = mediaRepository.findAll();
        assertThat(mediaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllMedia() throws Exception {
        // Initialize the database
        mediaRepository.save(media);

        // Get all the mediaList
        restMediaMockMvc.perform(get("/api/media?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(media.getId())))
            .andExpect(jsonPath("$.[*].mediaType").value(hasItem(DEFAULT_MEDIA_TYPE.toString())))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].fileUrl").value(hasItem(DEFAULT_FILE_URL.toString())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())));
    }
    
    @Test
    public void getMedia() throws Exception {
        // Initialize the database
        mediaRepository.save(media);

        // Get the media
        restMediaMockMvc.perform(get("/api/media/{id}", media.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(media.getId()))
            .andExpect(jsonPath("$.mediaType").value(DEFAULT_MEDIA_TYPE.toString()))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.fileUrl").value(DEFAULT_FILE_URL.toString()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()));
    }

    @Test
    public void getNonExistingMedia() throws Exception {
        // Get the media
        restMediaMockMvc.perform(get("/api/media/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateMedia() throws Exception {
        // Initialize the database
        mediaRepository.save(media);

        int databaseSizeBeforeUpdate = mediaRepository.findAll().size();

        // Update the media
        Media updatedMedia = mediaRepository.findById(media.getId()).get();
        updatedMedia
            .mediaType(UPDATED_MEDIA_TYPE)
            .createdOn(UPDATED_CREATED_ON)
            .fileUrl(UPDATED_FILE_URL)
            .title(UPDATED_TITLE);

        restMediaMockMvc.perform(put("/api/media")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMedia)))
            .andExpect(status().isOk());

        // Validate the Media in the database
        List<Media> mediaList = mediaRepository.findAll();
        assertThat(mediaList).hasSize(databaseSizeBeforeUpdate);
        Media testMedia = mediaList.get(mediaList.size() - 1);
        assertThat(testMedia.getMediaType()).isEqualTo(UPDATED_MEDIA_TYPE);
        assertThat(testMedia.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testMedia.getFileUrl()).isEqualTo(UPDATED_FILE_URL);
        assertThat(testMedia.getTitle()).isEqualTo(UPDATED_TITLE);
    }

    @Test
    public void updateNonExistingMedia() throws Exception {
        int databaseSizeBeforeUpdate = mediaRepository.findAll().size();

        // Create the Media

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMediaMockMvc.perform(put("/api/media")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(media)))
            .andExpect(status().isBadRequest());

        // Validate the Media in the database
        List<Media> mediaList = mediaRepository.findAll();
        assertThat(mediaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteMedia() throws Exception {
        // Initialize the database
        mediaRepository.save(media);

        int databaseSizeBeforeDelete = mediaRepository.findAll().size();

        // Get the media
        restMediaMockMvc.perform(delete("/api/media/{id}", media.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Media> mediaList = mediaRepository.findAll();
        assertThat(mediaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Media.class);
        Media media1 = new Media();
        media1.setId("id1");
        Media media2 = new Media();
        media2.setId(media1.getId());
        assertThat(media1).isEqualTo(media2);
        media2.setId("id2");
        assertThat(media1).isNotEqualTo(media2);
        media1.setId(null);
        assertThat(media1).isNotEqualTo(media2);
    }
}
