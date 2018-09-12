package io.github.jhipster.application.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

/**
 * A EventPhotos.
 */
@Document(collection = "event_photos")
public class EventPhotos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("eventid")
    private Long eventid;

    @Field("image_urls")
    private String imageUrls;

    @Field("likes")
    private String likes;

    @Field("seen")
    private String seen;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getEventid() {
        return eventid;
    }

    public EventPhotos eventid(Long eventid) {
        this.eventid = eventid;
        return this;
    }

    public void setEventid(Long eventid) {
        this.eventid = eventid;
    }

    public String getImageUrls() {
        return imageUrls;
    }

    public EventPhotos imageUrls(String imageUrls) {
        this.imageUrls = imageUrls;
        return this;
    }

    public void setImageUrls(String imageUrls) {
        this.imageUrls = imageUrls;
    }

    public String getLikes() {
        return likes;
    }

    public EventPhotos likes(String likes) {
        this.likes = likes;
        return this;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getSeen() {
        return seen;
    }

    public EventPhotos seen(String seen) {
        this.seen = seen;
        return this;
    }

    public void setSeen(String seen) {
        this.seen = seen;
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
        EventPhotos eventPhotos = (EventPhotos) o;
        if (eventPhotos.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), eventPhotos.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EventPhotos{" +
            "id=" + getId() +
            ", eventid=" + getEventid() +
            ", imageUrls='" + getImageUrls() + "'" +
            ", likes='" + getLikes() + "'" +
            ", seen='" + getSeen() + "'" +
            "}";
    }
}
