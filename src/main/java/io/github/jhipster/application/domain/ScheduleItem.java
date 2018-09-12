package io.github.jhipster.application.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import io.github.jhipster.application.domain.enumeration.ScheduleItemType;

/**
 * A ScheduleItem.
 */
@Document(collection = "schedule_item")
public class ScheduleItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("is_done")
    private Boolean isDone;

    @Field("item_type")
    private ScheduleItemType itemType;

    @Field("title")
    private String title;

    @Field("description")
    private String description;

    @Field("scheduled_date")
    private LocalDate scheduledDate;

    @Field("start_time")
    private LocalDate startTime;

    @Field("endt_time")
    private LocalDate endtTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean isIsDone() {
        return isDone;
    }

    public ScheduleItem isDone(Boolean isDone) {
        this.isDone = isDone;
        return this;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

    public ScheduleItemType getItemType() {
        return itemType;
    }

    public ScheduleItem itemType(ScheduleItemType itemType) {
        this.itemType = itemType;
        return this;
    }

    public void setItemType(ScheduleItemType itemType) {
        this.itemType = itemType;
    }

    public String getTitle() {
        return title;
    }

    public ScheduleItem title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public ScheduleItem description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getScheduledDate() {
        return scheduledDate;
    }

    public ScheduleItem scheduledDate(LocalDate scheduledDate) {
        this.scheduledDate = scheduledDate;
        return this;
    }

    public void setScheduledDate(LocalDate scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public ScheduleItem startTime(LocalDate startTime) {
        this.startTime = startTime;
        return this;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndtTime() {
        return endtTime;
    }

    public ScheduleItem endtTime(LocalDate endtTime) {
        this.endtTime = endtTime;
        return this;
    }

    public void setEndtTime(LocalDate endtTime) {
        this.endtTime = endtTime;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ScheduleItem scheduleItem = (ScheduleItem) o;
        if (scheduleItem.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), scheduleItem.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ScheduleItem{" +
            "id=" + getId() +
            ", isDone='" + isIsDone() + "'" +
            ", itemType='" + getItemType() + "'" +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", scheduledDate='" + getScheduledDate() + "'" +
            ", startTime='" + getStartTime() + "'" +
            ", endtTime='" + getEndtTime() + "'" +
            "}";
    }
}
