package io.github.jhipster.application.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A IncidentRecord.
 */
@Document(collection = "incident_record")
public class IncidentRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("nature_of_incident")
    private String natureOfIncident;

    @Field("first_aid_provided")
    private Boolean firstAidProvided;

    @Field("first_aid_notes")
    private String firstAidNotes;

    @Field("date")
    private LocalDate date;

    @Field("created_on")
    private LocalDate createdOn;

    @Field("is_admin_only")
    private Boolean isAdminOnly;

    @Field("notes")
    private String notes;

    @Field("students")
    private String students;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNatureOfIncident() {
        return natureOfIncident;
    }

    public IncidentRecord natureOfIncident(String natureOfIncident) {
        this.natureOfIncident = natureOfIncident;
        return this;
    }

    public void setNatureOfIncident(String natureOfIncident) {
        this.natureOfIncident = natureOfIncident;
    }

    public Boolean isFirstAidProvided() {
        return firstAidProvided;
    }

    public IncidentRecord firstAidProvided(Boolean firstAidProvided) {
        this.firstAidProvided = firstAidProvided;
        return this;
    }

    public void setFirstAidProvided(Boolean firstAidProvided) {
        this.firstAidProvided = firstAidProvided;
    }

    public String getFirstAidNotes() {
        return firstAidNotes;
    }

    public IncidentRecord firstAidNotes(String firstAidNotes) {
        this.firstAidNotes = firstAidNotes;
        return this;
    }

    public void setFirstAidNotes(String firstAidNotes) {
        this.firstAidNotes = firstAidNotes;
    }

    public LocalDate getDate() {
        return date;
    }

    public IncidentRecord date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public IncidentRecord createdOn(LocalDate createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public Boolean isIsAdminOnly() {
        return isAdminOnly;
    }

    public IncidentRecord isAdminOnly(Boolean isAdminOnly) {
        this.isAdminOnly = isAdminOnly;
        return this;
    }

    public void setIsAdminOnly(Boolean isAdminOnly) {
        this.isAdminOnly = isAdminOnly;
    }

    public String getNotes() {
        return notes;
    }

    public IncidentRecord notes(String notes) {
        this.notes = notes;
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStudents() {
        return students;
    }

    public IncidentRecord students(String students) {
        this.students = students;
        return this;
    }

    public void setStudents(String students) {
        this.students = students;
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
        IncidentRecord incidentRecord = (IncidentRecord) o;
        if (incidentRecord.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), incidentRecord.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "IncidentRecord{" +
            "id=" + getId() +
            ", natureOfIncident='" + getNatureOfIncident() + "'" +
            ", firstAidProvided='" + isFirstAidProvided() + "'" +
            ", firstAidNotes='" + getFirstAidNotes() + "'" +
            ", date='" + getDate() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", isAdminOnly='" + isIsAdminOnly() + "'" +
            ", notes='" + getNotes() + "'" +
            ", students='" + getStudents() + "'" +
            "}";
    }
}
