package io.github.jhipster.application.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A ImmunizationRecord.
 */
@Document(collection = "immunization_record")
public class ImmunizationRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("immunizationitem_id")
    private Long immunizationitemId;

    @Field("created_on")
    private LocalDate createdOn;

    @Field("vaccination_done_on")
    private LocalDate vaccinationDoneOn;

    @Field("vaccination_name")
    private String vaccinationName;

    @Field("is_on_time")
    private Boolean isOnTime;

    @Field("image_url")
    private String imageUrl;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getImmunizationitemId() {
        return immunizationitemId;
    }

    public ImmunizationRecord immunizationitemId(Long immunizationitemId) {
        this.immunizationitemId = immunizationitemId;
        return this;
    }

    public void setImmunizationitemId(Long immunizationitemId) {
        this.immunizationitemId = immunizationitemId;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public ImmunizationRecord createdOn(LocalDate createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDate getVaccinationDoneOn() {
        return vaccinationDoneOn;
    }

    public ImmunizationRecord vaccinationDoneOn(LocalDate vaccinationDoneOn) {
        this.vaccinationDoneOn = vaccinationDoneOn;
        return this;
    }

    public void setVaccinationDoneOn(LocalDate vaccinationDoneOn) {
        this.vaccinationDoneOn = vaccinationDoneOn;
    }

    public String getVaccinationName() {
        return vaccinationName;
    }

    public ImmunizationRecord vaccinationName(String vaccinationName) {
        this.vaccinationName = vaccinationName;
        return this;
    }

    public void setVaccinationName(String vaccinationName) {
        this.vaccinationName = vaccinationName;
    }

    public Boolean isIsOnTime() {
        return isOnTime;
    }

    public ImmunizationRecord isOnTime(Boolean isOnTime) {
        this.isOnTime = isOnTime;
        return this;
    }

    public void setIsOnTime(Boolean isOnTime) {
        this.isOnTime = isOnTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ImmunizationRecord imageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
        ImmunizationRecord immunizationRecord = (ImmunizationRecord) o;
        if (immunizationRecord.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), immunizationRecord.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ImmunizationRecord{" +
            "id=" + getId() +
            ", immunizationitemId=" + getImmunizationitemId() +
            ", createdOn='" + getCreatedOn() + "'" +
            ", vaccinationDoneOn='" + getVaccinationDoneOn() + "'" +
            ", vaccinationName='" + getVaccinationName() + "'" +
            ", isOnTime='" + isIsOnTime() + "'" +
            ", imageUrl='" + getImageUrl() + "'" +
            "}";
    }
}
