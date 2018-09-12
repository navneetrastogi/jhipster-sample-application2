package io.github.jhipster.application.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Instruction.
 */
@Document(collection = "instruction")
public class Instruction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("schedule_item_id")
    private Long scheduleItemId;

    @Field("video_url")
    private String videoURL;

    @Field("photo_ur_ls")
    private String photoURLs;

    @Field("instruction")
    private String instruction;

    @Field("title")
    private String title;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getScheduleItemId() {
        return scheduleItemId;
    }

    public Instruction scheduleItemId(Long scheduleItemId) {
        this.scheduleItemId = scheduleItemId;
        return this;
    }

    public void setScheduleItemId(Long scheduleItemId) {
        this.scheduleItemId = scheduleItemId;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public Instruction videoURL(String videoURL) {
        this.videoURL = videoURL;
        return this;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getPhotoURLs() {
        return photoURLs;
    }

    public Instruction photoURLs(String photoURLs) {
        this.photoURLs = photoURLs;
        return this;
    }

    public void setPhotoURLs(String photoURLs) {
        this.photoURLs = photoURLs;
    }

    public String getInstruction() {
        return instruction;
    }

    public Instruction instruction(String instruction) {
        this.instruction = instruction;
        return this;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getTitle() {
        return title;
    }

    public Instruction title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
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
        Instruction instruction = (Instruction) o;
        if (instruction.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), instruction.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Instruction{" +
            "id=" + getId() +
            ", scheduleItemId=" + getScheduleItemId() +
            ", videoURL='" + getVideoURL() + "'" +
            ", photoURLs='" + getPhotoURLs() + "'" +
            ", instruction='" + getInstruction() + "'" +
            ", title='" + getTitle() + "'" +
            "}";
    }
}
