package io.github.jhipster.application.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import io.github.jhipster.application.domain.enumeration.Relation;

/**
 * A Parent.
 */
@Document(collection = "parent")
public class Parent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("created_on")
    private LocalDate createdOn;

    @Field("phone_number")
    private String phoneNumber;

    @Field("relation")
    private Relation relation;

    @Field("student_name")
    private String studentName;

    @Field("is_account_active")
    private Boolean isAccountActive;

    @Field("email")
    private String email;

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

    public Parent name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public Parent createdOn(LocalDate createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Parent phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Relation getRelation() {
        return relation;
    }

    public Parent relation(Relation relation) {
        this.relation = relation;
        return this;
    }

    public void setRelation(Relation relation) {
        this.relation = relation;
    }

    public String getStudentName() {
        return studentName;
    }

    public Parent studentName(String studentName) {
        this.studentName = studentName;
        return this;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Boolean isIsAccountActive() {
        return isAccountActive;
    }

    public Parent isAccountActive(Boolean isAccountActive) {
        this.isAccountActive = isAccountActive;
        return this;
    }

    public void setIsAccountActive(Boolean isAccountActive) {
        this.isAccountActive = isAccountActive;
    }

    public String getEmail() {
        return email;
    }

    public Parent email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
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
        Parent parent = (Parent) o;
        if (parent.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), parent.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Parent{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", relation='" + getRelation() + "'" +
            ", studentName='" + getStudentName() + "'" +
            ", isAccountActive='" + isIsAccountActive() + "'" +
            ", email='" + getEmail() + "'" +
            "}";
    }
}
