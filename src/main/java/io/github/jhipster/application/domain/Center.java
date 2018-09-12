package io.github.jhipster.application.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Center.
 */
@Document(collection = "center")
public class Center implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("created_on")
    private LocalDate createdOn;

    @Field("last_modified_on")
    private LocalDate lastModifiedOn;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Center name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public Center createdOn(LocalDate createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDate getLastModifiedOn() {
        return lastModifiedOn;
    }

    public Center lastModifiedOn(LocalDate lastModifiedOn) {
        this.lastModifiedOn = lastModifiedOn;
        return this;
    }

    public void setLastModifiedOn(LocalDate lastModifiedOn) {
        this.lastModifiedOn = lastModifiedOn;
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
        Center center = (Center) o;
        if (center.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), center.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Center{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", lastModifiedOn='" + getLastModifiedOn() + "'" +
            "}";
    }
}
