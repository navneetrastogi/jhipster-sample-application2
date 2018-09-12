package io.github.jhipster.application.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ImmunizationPlan.
 */
@Document(collection = "immunization_plan")
public class ImmunizationPlan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("immunization_items")
    private String immunizationItems;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImmunizationItems() {
        return immunizationItems;
    }

    public ImmunizationPlan immunizationItems(String immunizationItems) {
        this.immunizationItems = immunizationItems;
        return this;
    }

    public void setImmunizationItems(String immunizationItems) {
        this.immunizationItems = immunizationItems;
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
        ImmunizationPlan immunizationPlan = (ImmunizationPlan) o;
        if (immunizationPlan.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), immunizationPlan.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ImmunizationPlan{" +
            "id=" + getId() +
            ", immunizationItems='" + getImmunizationItems() + "'" +
            "}";
    }
}
