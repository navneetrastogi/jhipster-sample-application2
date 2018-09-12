package io.github.jhipster.application.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A MilestoneRecord.
 */
@Document(collection = "milestone_record")
public class MilestoneRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("created_on")
    private LocalDate createdOn;

    @Field("notes")
    private String notes;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public MilestoneRecord createdOn(LocalDate createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getNotes() {
        return notes;
    }

    public MilestoneRecord notes(String notes) {
        this.notes = notes;
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
        MilestoneRecord milestoneRecord = (MilestoneRecord) o;
        if (milestoneRecord.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), milestoneRecord.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MilestoneRecord{" +
            "id=" + getId() +
            ", createdOn='" + getCreatedOn() + "'" +
            ", notes='" + getNotes() + "'" +
            "}";
    }
}
