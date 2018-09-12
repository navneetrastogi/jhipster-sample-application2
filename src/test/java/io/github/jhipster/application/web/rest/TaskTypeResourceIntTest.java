package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplication2App;

import io.github.jhipster.application.domain.TaskType;
import io.github.jhipster.application.repository.TaskTypeRepository;
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
 * Test class for the TaskTypeResource REST controller.
 *
 * @see TaskTypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplication2App.class)
public class TaskTypeResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_PRIORITY = 1;
    private static final Integer UPDATED_PRIORITY = 2;

    @Autowired
    private TaskTypeRepository taskTypeRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restTaskTypeMockMvc;

    private TaskType taskType;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TaskTypeResource taskTypeResource = new TaskTypeResource(taskTypeRepository);
        this.restTaskTypeMockMvc = MockMvcBuilders.standaloneSetup(taskTypeResource)
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
    public static TaskType createEntity() {
        TaskType taskType = new TaskType()
            .name(DEFAULT_NAME)
            .priority(DEFAULT_PRIORITY);
        return taskType;
    }

    @Before
    public void initTest() {
        taskTypeRepository.deleteAll();
        taskType = createEntity();
    }

    @Test
    public void createTaskType() throws Exception {
        int databaseSizeBeforeCreate = taskTypeRepository.findAll().size();

        // Create the TaskType
        restTaskTypeMockMvc.perform(post("/api/task-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taskType)))
            .andExpect(status().isCreated());

        // Validate the TaskType in the database
        List<TaskType> taskTypeList = taskTypeRepository.findAll();
        assertThat(taskTypeList).hasSize(databaseSizeBeforeCreate + 1);
        TaskType testTaskType = taskTypeList.get(taskTypeList.size() - 1);
        assertThat(testTaskType.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTaskType.getPriority()).isEqualTo(DEFAULT_PRIORITY);
    }

    @Test
    public void createTaskTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = taskTypeRepository.findAll().size();

        // Create the TaskType with an existing ID
        taskType.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restTaskTypeMockMvc.perform(post("/api/task-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taskType)))
            .andExpect(status().isBadRequest());

        // Validate the TaskType in the database
        List<TaskType> taskTypeList = taskTypeRepository.findAll();
        assertThat(taskTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllTaskTypes() throws Exception {
        // Initialize the database
        taskTypeRepository.save(taskType);

        // Get all the taskTypeList
        restTaskTypeMockMvc.perform(get("/api/task-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(taskType.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].priority").value(hasItem(DEFAULT_PRIORITY)));
    }
    
    @Test
    public void getTaskType() throws Exception {
        // Initialize the database
        taskTypeRepository.save(taskType);

        // Get the taskType
        restTaskTypeMockMvc.perform(get("/api/task-types/{id}", taskType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(taskType.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.priority").value(DEFAULT_PRIORITY));
    }

    @Test
    public void getNonExistingTaskType() throws Exception {
        // Get the taskType
        restTaskTypeMockMvc.perform(get("/api/task-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateTaskType() throws Exception {
        // Initialize the database
        taskTypeRepository.save(taskType);

        int databaseSizeBeforeUpdate = taskTypeRepository.findAll().size();

        // Update the taskType
        TaskType updatedTaskType = taskTypeRepository.findById(taskType.getId()).get();
        updatedTaskType
            .name(UPDATED_NAME)
            .priority(UPDATED_PRIORITY);

        restTaskTypeMockMvc.perform(put("/api/task-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTaskType)))
            .andExpect(status().isOk());

        // Validate the TaskType in the database
        List<TaskType> taskTypeList = taskTypeRepository.findAll();
        assertThat(taskTypeList).hasSize(databaseSizeBeforeUpdate);
        TaskType testTaskType = taskTypeList.get(taskTypeList.size() - 1);
        assertThat(testTaskType.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTaskType.getPriority()).isEqualTo(UPDATED_PRIORITY);
    }

    @Test
    public void updateNonExistingTaskType() throws Exception {
        int databaseSizeBeforeUpdate = taskTypeRepository.findAll().size();

        // Create the TaskType

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaskTypeMockMvc.perform(put("/api/task-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taskType)))
            .andExpect(status().isBadRequest());

        // Validate the TaskType in the database
        List<TaskType> taskTypeList = taskTypeRepository.findAll();
        assertThat(taskTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteTaskType() throws Exception {
        // Initialize the database
        taskTypeRepository.save(taskType);

        int databaseSizeBeforeDelete = taskTypeRepository.findAll().size();

        // Get the taskType
        restTaskTypeMockMvc.perform(delete("/api/task-types/{id}", taskType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TaskType> taskTypeList = taskTypeRepository.findAll();
        assertThat(taskTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaskType.class);
        TaskType taskType1 = new TaskType();
        taskType1.setId("id1");
        TaskType taskType2 = new TaskType();
        taskType2.setId(taskType1.getId());
        assertThat(taskType1).isEqualTo(taskType2);
        taskType2.setId("id2");
        assertThat(taskType1).isNotEqualTo(taskType2);
        taskType1.setId(null);
        assertThat(taskType1).isNotEqualTo(taskType2);
    }
}
