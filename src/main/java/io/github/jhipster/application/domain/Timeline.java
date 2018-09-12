package io.github.jhipster.application.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Timeline.
 */
@Document(collection = "timeline")
public class Timeline implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("date")
    private LocalDate date;

    @Field("is_visible")
    private LocalDate isVisible;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Timeline date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getIsVisible() {
        return isVisible;
    }

    public Timeline isVisible(LocalDate isVisible) {
        this.isVisible = isVisible;
        return this;
    }

    public void setIsVisible(LocalDate isVisible) {
        this.isVisible = isVisible;
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
        Timeline timeline = (Timeline) o;
        if (timeline.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), timeline.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Timeline{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", isVisible='" + getIsVisible() + "'" +
            "}";
    }
}
