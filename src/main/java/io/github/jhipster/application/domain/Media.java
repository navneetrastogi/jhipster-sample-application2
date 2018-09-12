package io.github.jhipster.application.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import io.github.jhipster.application.domain.enumeration.IllumineMediaType;

/**
 * A Media.
 */
@Document(collection = "media")
public class Media implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("media_type")
    private IllumineMediaType mediaType;

    @Field("created_on")
    private LocalDate createdOn;

    @Field("file_url")
    private String fileUrl;

    @Field("title")
    private String title;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public IllumineMediaType getMediaType() {
        return mediaType;
    }

    public Media mediaType(IllumineMediaType mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    public void setMediaType(IllumineMediaType mediaType) {
        this.mediaType = mediaType;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public Media createdOn(LocalDate createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public Media fileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
        return this;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getTitle() {
        return title;
    }

    public Media title(String title) {
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
        Media media = (Media) o;
        if (media.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), media.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Media{" +
            "id=" + getId() +
            ", mediaType='" + getMediaType() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", fileUrl='" + getFileUrl() + "'" +
            ", title='" + getTitle() + "'" +
            "}";
    }
}
