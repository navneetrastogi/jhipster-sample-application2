package io.github.jhipster.application.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import io.github.jhipster.application.domain.enumeration.Gender;

/**
 * A StudentProfile.
 */
@Document(collection = "student_profile")
public class StudentProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("dream")
    private String dream;

    @Field("birth_date")
    private LocalDate birthDate;

    @Field("blood_group")
    private String bloodGroup;

    @Field("gender")
    private Gender gender;

    @Field("allergies")
    private String allergies;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDream() {
        return dream;
    }

    public StudentProfile dream(String dream) {
        this.dream = dream;
        return this;
    }

    public void setDream(String dream) {
        this.dream = dream;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public StudentProfile birthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public StudentProfile bloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
        return this;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Gender getGender() {
        return gender;
    }

    public StudentProfile gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAllergies() {
        return allergies;
    }

    public StudentProfile allergies(String allergies) {
        this.allergies = allergies;
        return this;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
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
        StudentProfile studentProfile = (StudentProfile) o;
        if (studentProfile.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), studentProfile.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StudentProfile{" +
            "id=" + getId() +
            ", dream='" + getDream() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", bloodGroup='" + getBloodGroup() + "'" +
            ", gender='" + getGender() + "'" +
            ", allergies='" + getAllergies() + "'" +
            "}";
    }
}
