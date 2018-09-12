package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplication2App;

import io.github.jhipster.application.domain.Timeline;
import io.github.jhipster.application.repository.TimelineRepository;
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
 * Test class for the TimelineResource REST controller.
 *
 * @see TimelineResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplication2App.class)
public class TimelineResourceIntTest {

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_IS_VISIBLE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_IS_VISIBLE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private TimelineRepository timelineRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restTimelineMockMvc;

    private Timeline timeline;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TimelineResource timelineResource = new TimelineResource(timelineRepository);
        this.restTimelineMockMvc = MockMvcBuilders.standaloneSetup(timelineResource)
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
    public static Timeline createEntity() {
        Timeline timeline = new Timeline()
            .date(DEFAULT_DATE)
            .isVisible(DEFAULT_IS_VISIBLE);
        return timeline;
    }

    @Before
    public void initTest() {
        timelineRepository.deleteAll();
        timeline = createEntity();
    }

    @Test
    public void createTimeline() throws Exception {
        int databaseSizeBeforeCreate = timelineRepository.findAll().size();

        // Create the Timeline
        restTimelineMockMvc.perform(post("/api/timelines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(timeline)))
            .andExpect(status().isCreated());

        // Validate the Timeline in the database
        List<Timeline> timelineList = timelineRepository.findAll();
        assertThat(timelineList).hasSize(databaseSizeBeforeCreate + 1);
        Timeline testTimeline = timelineList.get(timelineList.size() - 1);
        assertThat(testTimeline.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testTimeline.getIsVisible()).isEqualTo(DEFAULT_IS_VISIBLE);
    }

    @Test
    public void createTimelineWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = timelineRepository.findAll().size();

        // Create the Timeline with an existing ID
        timeline.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restTimelineMockMvc.perform(post("/api/timelines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(timeline)))
            .andExpect(status().isBadRequest());

        // Validate the Timeline in the database
        List<Timeline> timelineList = timelineRepository.findAll();
        assertThat(timelineList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllTimelines() throws Exception {
        // Initialize the database
        timelineRepository.save(timeline);

        // Get all the timelineList
        restTimelineMockMvc.perform(get("/api/timelines?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(timeline.getId())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].isVisible").value(hasItem(DEFAULT_IS_VISIBLE.toString())));
    }
    
    @Test
    public void getTimeline() throws Exception {
        // Initialize the database
        timelineRepository.save(timeline);

        // Get the timeline
        restTimelineMockMvc.perform(get("/api/timelines/{id}", timeline.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(timeline.getId()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.isVisible").value(DEFAULT_IS_VISIBLE.toString()));
    }

    @Test
    public void getNonExistingTimeline() throws Exception {
        // Get the timeline
        restTimelineMockMvc.perform(get("/api/timelines/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateTimeline() throws Exception {
        // Initialize the database
        timelineRepository.save(timeline);

        int databaseSizeBeforeUpdate = timelineRepository.findAll().size();

        // Update the timeline
        Timeline updatedTimeline = timelineRepository.findById(timeline.getId()).get();
        updatedTimeline
            .date(UPDATED_DATE)
            .isVisible(UPDATED_IS_VISIBLE);

        restTimelineMockMvc.perform(put("/api/timelines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTimeline)))
            .andExpect(status().isOk());

        // Validate the Timeline in the database
        List<Timeline> timelineList = timelineRepository.findAll();
        assertThat(timelineList).hasSize(databaseSizeBeforeUpdate);
        Timeline testTimeline = timelineList.get(timelineList.size() - 1);
        assertThat(testTimeline.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testTimeline.getIsVisible()).isEqualTo(UPDATED_IS_VISIBLE);
    }

    @Test
    public void updateNonExistingTimeline() throws Exception {
        int databaseSizeBeforeUpdate = timelineRepository.findAll().size();

        // Create the Timeline

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTimelineMockMvc.perform(put("/api/timelines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(timeline)))
            .andExpect(status().isBadRequest());

        // Validate the Timeline in the database
        List<Timeline> timelineList = timelineRepository.findAll();
        assertThat(timelineList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteTimeline() throws Exception {
        // Initialize the database
        timelineRepository.save(timeline);

        int databaseSizeBeforeDelete = timelineRepository.findAll().size();

        // Get the timeline
        restTimelineMockMvc.perform(delete("/api/timelines/{id}", timeline.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Timeline> timelineList = timelineRepository.findAll();
        assertThat(timelineList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Timeline.class);
        Timeline timeline1 = new Timeline();
        timeline1.setId("id1");
        Timeline timeline2 = new Timeline();
        timeline2.setId(timeline1.getId());
        assertThat(timeline1).isEqualTo(timeline2);
        timeline2.setId("id2");
        assertThat(timeline1).isNotEqualTo(timeline2);
        timeline1.setId(null);
        assertThat(timeline1).isNotEqualTo(timeline2);
    }
}
