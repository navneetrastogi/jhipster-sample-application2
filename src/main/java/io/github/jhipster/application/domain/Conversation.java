package io.github.jhipster.application.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import io.github.jhipster.application.domain.enumeration.IllumineMediaType;

/**
 * A Conversation.
 */
@Document(collection = "conversation")
public class Conversation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("text")
    private String text;

    @Field("created_on")
    private LocalDate createdOn;

    @Field("sent_on")
    private LocalDate sentOn;

    @Field("status")
    private String status;

    @Field("file_url")
    private String fileUrl;

    @Field("media_type")
    private IllumineMediaType mediaType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public Conversation text(String text) {
        this.text = text;
        return this;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public Conversation createdOn(LocalDate createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDate getSentOn() {
        return sentOn;
    }

    public Conversation sentOn(LocalDate sentOn) {
        this.sentOn = sentOn;
        return this;
    }

    public void setSentOn(LocalDate sentOn) {
        this.sentOn = sentOn;
    }

    public String getStatus() {
        return status;
    }

    public Conversation status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public Conversation fileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
        return this;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public IllumineMediaType getMediaType() {
        return mediaType;
    }

    public Conversation mediaType(IllumineMediaType mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    public void setMediaType(IllumineMediaType mediaType) {
        this.mediaType = mediaType;
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
        Conversation conversation = (Conversation) o;
        if (conversation.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), conversation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Conversation{" +
            "id=" + getId() +
            ", text='" + getText() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", sentOn='" + getSentOn() + "'" +
            ", status='" + getStatus() + "'" +
            ", fileUrl='" + getFileUrl() + "'" +
            ", mediaType='" + getMediaType() + "'" +
            "}";
    }
}
