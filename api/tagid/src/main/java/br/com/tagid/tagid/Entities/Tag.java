package br.com.tagid.tagid.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "T_IOT_TAG")
public class Tag {
    @Id
    @Column(name = "cd_tag")
    private String tagId;
    @Column(name = "ds_tagid_")
    private String description;
    @Column(name = "st_tagid")
    private boolean status;

    public Tag(String tagId) {
        this.tagId = tagId;
        this.status = false;
    }

    public Tag() {

    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
