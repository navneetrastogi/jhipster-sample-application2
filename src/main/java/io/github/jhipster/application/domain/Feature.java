package io.github.jhipster.application.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

import io.github.jhipster.application.domain.enumeration.FeatureType;

/**
 * A Feature.
 */
@Document(collection = "feature")
public class Feature implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("feature_type")
    private FeatureType featureType;

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

    public Feature name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FeatureType getFeatureType() {
        return featureType;
    }

    public Feature featureType(FeatureType featureType) {
        this.featureType = featureType;
        return this;
    }

    public void setFeatureType(FeatureType featureType) {
        this.featureType = featureType;
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
        Feature feature = (Feature) o;
        if (feature.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), feature.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Feature{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", featureType='" + getFeatureType() + "'" +
            "}";
    }
}
