package io.github.jhipster.application.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ImmunizationItems.
 */
@Document(collection = "immunization_items")
public class ImmunizationItems implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("title")
    private String title;

    @Field("description")
    private String description;

    @Field("start_age")
    private Integer startAge;

    @Field("end_age")
    private Integer endAge;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public ImmunizationItems title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public ImmunizationItems description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStartAge() {
        return startAge;
    }

    public ImmunizationItems startAge(Integer startAge) {
        this.startAge = startAge;
        return this;
    }

    public void setStartAge(Integer startAge) {
        this.startAge = startAge;
    }

    public Integer getEndAge() {
        return endAge;
    }

    public ImmunizationItems endAge(Integer endAge) {
        this.endAge = endAge;
        return this;
    }

    public void setEndAge(Integer endAge) {
        this.endAge = endAge;
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
        ImmunizationItems immunizationItems = (ImmunizationItems) o;
        if (immunizationItems.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), immunizationItems.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ImmunizationItems{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", startAge=" + getStartAge() +
            ", endAge=" + getEndAge() +
            "}";
    }
}
