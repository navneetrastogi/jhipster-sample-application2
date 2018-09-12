package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplication2App;

import io.github.jhipster.application.domain.ScheduleItem;
import io.github.jhipster.application.repository.ScheduleItemRepository;
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

import io.github.jhipster.application.domain.enumeration.ScheduleItemType;
/**
 * Test class for the ScheduleItemResource REST controller.
 *
 * @see ScheduleItemResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplication2App.class)
public class ScheduleItemResourceIntTest {

    private static final Boolean DEFAULT_IS_DONE = false;
    private static final Boolean UPDATED_IS_DONE = true;

    private static final ScheduleItemType DEFAULT_ITEM_TYPE = ScheduleItemType.EVENT;
    private static final ScheduleItemType UPDATED_ITEM_TYPE = ScheduleItemType.ACTIVITY;

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_SCHEDULED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SCHEDULED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_START_TIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_START_TIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ENDT_TIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ENDT_TIME = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private ScheduleItemRepository scheduleItemRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restScheduleItemMockMvc;

    private ScheduleItem scheduleItem;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ScheduleItemResource scheduleItemResource = new ScheduleItemResource(scheduleItemRepository);
        this.restScheduleItemMockMvc = MockMvcBuilders.standaloneSetup(scheduleItemResource)
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
    public static ScheduleItem createEntity() {
        ScheduleItem scheduleItem = new ScheduleItem()
            .isDone(DEFAULT_IS_DONE)
            .itemType(DEFAULT_ITEM_TYPE)
            .title(DEFAULT_TITLE)
            .description(DEFAULT_DESCRIPTION)
            .scheduledDate(DEFAULT_SCHEDULED_DATE)
            .startTime(DEFAULT_START_TIME)
            .endtTime(DEFAULT_ENDT_TIME);
        return scheduleItem;
    }

    @Before
    public void initTest() {
        scheduleItemRepository.deleteAll();
        scheduleItem = createEntity();
    }

    @Test
    public void createScheduleItem() throws Exception {
        int databaseSizeBeforeCreate = scheduleItemRepository.findAll().size();

        // Create the ScheduleItem
        restScheduleItemMockMvc.perform(post("/api/schedule-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(scheduleItem)))
            .andExpect(status().isCreated());

        // Validate the ScheduleItem in the database
        List<ScheduleItem> scheduleItemList = scheduleItemRepository.findAll();
        assertThat(scheduleItemList).hasSize(databaseSizeBeforeCreate + 1);
        ScheduleItem testScheduleItem = scheduleItemList.get(scheduleItemList.size() - 1);
        assertThat(testScheduleItem.isIsDone()).isEqualTo(DEFAULT_IS_DONE);
        assertThat(testScheduleItem.getItemType()).isEqualTo(DEFAULT_ITEM_TYPE);
        assertThat(testScheduleItem.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testScheduleItem.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testScheduleItem.getScheduledDate()).isEqualTo(DEFAULT_SCHEDULED_DATE);
        assertThat(testScheduleItem.getStartTime()).isEqualTo(DEFAULT_START_TIME);
        assertThat(testScheduleItem.getEndtTime()).isEqualTo(DEFAULT_ENDT_TIME);
    }

    @Test
    public void createScheduleItemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = scheduleItemRepository.findAll().size();

        // Create the ScheduleItem with an existing ID
        scheduleItem.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restScheduleItemMockMvc.perform(post("/api/schedule-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(scheduleItem)))
            .andExpect(status().isBadRequest());

        // Validate the ScheduleItem in the database
        List<ScheduleItem> scheduleItemList = scheduleItemRepository.findAll();
        assertThat(scheduleItemList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllScheduleItems() throws Exception {
        // Initialize the database
        scheduleItemRepository.save(scheduleItem);

        // Get all the scheduleItemList
        restScheduleItemMockMvc.perform(get("/api/schedule-items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(scheduleItem.getId())))
            .andExpect(jsonPath("$.[*].isDone").value(hasItem(DEFAULT_IS_DONE.booleanValue())))
            .andExpect(jsonPath("$.[*].itemType").value(hasItem(DEFAULT_ITEM_TYPE.toString())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].scheduledDate").value(hasItem(DEFAULT_SCHEDULED_DATE.toString())))
            .andExpect(jsonPath("$.[*].startTime").value(hasItem(DEFAULT_START_TIME.toString())))
            .andExpect(jsonPath("$.[*].endtTime").value(hasItem(DEFAULT_ENDT_TIME.toString())));
    }
    
    @Test
    public void getScheduleItem() throws Exception {
        // Initialize the database
        scheduleItemRepository.save(scheduleItem);

        // Get the scheduleItem
        restScheduleItemMockMvc.perform(get("/api/schedule-items/{id}", scheduleItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(scheduleItem.getId()))
            .andExpect(jsonPath("$.isDone").value(DEFAULT_IS_DONE.booleanValue()))
            .andExpect(jsonPath("$.itemType").value(DEFAULT_ITEM_TYPE.toString()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.scheduledDate").value(DEFAULT_SCHEDULED_DATE.toString()))
            .andExpect(jsonPath("$.startTime").value(DEFAULT_START_TIME.toString()))
            .andExpect(jsonPath("$.endtTime").value(DEFAULT_ENDT_TIME.toString()));
    }

    @Test
    public void getNonExistingScheduleItem() throws Exception {
        // Get the scheduleItem
        restScheduleItemMockMvc.perform(get("/api/schedule-items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateScheduleItem() throws Exception {
        // Initialize the database
        scheduleItemRepository.save(scheduleItem);

        int databaseSizeBeforeUpdate = scheduleItemRepository.findAll().size();

        // Update the scheduleItem
        ScheduleItem updatedScheduleItem = scheduleItemRepository.findById(scheduleItem.getId()).get();
        updatedScheduleItem
            .isDone(UPDATED_IS_DONE)
            .itemType(UPDATED_ITEM_TYPE)
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .scheduledDate(UPDATED_SCHEDULED_DATE)
            .startTime(UPDATED_START_TIME)
            .endtTime(UPDATED_ENDT_TIME);

        restScheduleItemMockMvc.perform(put("/api/schedule-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedScheduleItem)))
            .andExpect(status().isOk());

        // Validate the ScheduleItem in the database
        List<ScheduleItem> scheduleItemList = scheduleItemRepository.findAll();
        assertThat(scheduleItemList).hasSize(databaseSizeBeforeUpdate);
        ScheduleItem testScheduleItem = scheduleItemList.get(scheduleItemList.size() - 1);
        assertThat(testScheduleItem.isIsDone()).isEqualTo(UPDATED_IS_DONE);
        assertThat(testScheduleItem.getItemType()).isEqualTo(UPDATED_ITEM_TYPE);
        assertThat(testScheduleItem.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testScheduleItem.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testScheduleItem.getScheduledDate()).isEqualTo(UPDATED_SCHEDULED_DATE);
        assertThat(testScheduleItem.getStartTime()).isEqualTo(UPDATED_START_TIME);
        assertThat(testScheduleItem.getEndtTime()).isEqualTo(UPDATED_ENDT_TIME);
    }

    @Test
    public void updateNonExistingScheduleItem() throws Exception {
        int databaseSizeBeforeUpdate = scheduleItemRepository.findAll().size();

        // Create the ScheduleItem

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restScheduleItemMockMvc.perform(put("/api/schedule-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(scheduleItem)))
            .andExpect(status().isBadRequest());

        // Validate the ScheduleItem in the database
        List<ScheduleItem> scheduleItemList = scheduleItemRepository.findAll();
        assertThat(scheduleItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteScheduleItem() throws Exception {
        // Initialize the database
        scheduleItemRepository.save(scheduleItem);

        int databaseSizeBeforeDelete = scheduleItemRepository.findAll().size();

        // Get the scheduleItem
        restScheduleItemMockMvc.perform(delete("/api/schedule-items/{id}", scheduleItem.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ScheduleItem> scheduleItemList = scheduleItemRepository.findAll();
        assertThat(scheduleItemList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ScheduleItem.class);
        ScheduleItem scheduleItem1 = new ScheduleItem();
        scheduleItem1.setId("id1");
        ScheduleItem scheduleItem2 = new ScheduleItem();
        scheduleItem2.setId(scheduleItem1.getId());
        assertThat(scheduleItem1).isEqualTo(scheduleItem2);
        scheduleItem2.setId("id2");
        assertThat(scheduleItem1).isNotEqualTo(scheduleItem2);
        scheduleItem1.setId(null);
        assertThat(scheduleItem1).isNotEqualTo(scheduleItem2);
    }
}
