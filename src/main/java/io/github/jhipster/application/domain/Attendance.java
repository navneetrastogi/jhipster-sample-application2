package io.github.jhipster.application.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Attendance.
 */
@Document(collection = "attendance")
public class Attendance implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("checkin")
    private LocalDate checkin;

    @Field("checkout")
    private LocalDate checkout;

    @Field("datetime")
    private LocalDate datetime;

    @Field("created_on")
    private LocalDate createdOn;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getCheckin() {
        return checkin;
    }

    public Attendance checkin(LocalDate checkin) {
        this.checkin = checkin;
        return this;
    }

    public void setCheckin(LocalDate checkin) {
        this.checkin = checkin;
    }

    public LocalDate getCheckout() {
        return checkout;
    }

    public Attendance checkout(LocalDate checkout) {
        this.checkout = checkout;
        return this;
    }

    public void setCheckout(LocalDate checkout) {
        this.checkout = checkout;
    }

    public LocalDate getDatetime() {
        return datetime;
    }

    public Attendance datetime(LocalDate datetime) {
        this.datetime = datetime;
        return this;
    }

    public void setDatetime(LocalDate datetime) {
        this.datetime = datetime;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public Attendance createdOn(LocalDate createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
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
        Attendance attendance = (Attendance) o;
        if (attendance.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), attendance.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Attendance{" +
            "id=" + getId() +
            ", checkin='" + getCheckin() + "'" +
            ", checkout='" + getCheckout() + "'" +
            ", datetime='" + getDatetime() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            "}";
    }
}
