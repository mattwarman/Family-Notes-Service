package com.tzprod.familynotes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "notes")
public class Notes {
    @Id
    @Column(name = "idnote")
    private Integer idnote;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @Column(name = "image")
    private byte[] image;

    @Column(name = "time")
    private LocalDateTime time;

    @Column(name = "userid")
    private Integer userid;

    @Column(name = "topicid")
    private Integer topicid;

    public Integer getIdnote() {
        return this.idnote;
    }

    public void setIdnote(Integer idnote) {
        this.idnote = idnote;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public byte[] getImage() {
        return this.image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public LocalDateTime getTime() {
        return this.time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Integer getUserid() {
        return this.userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getTopicid() {
        return this.topicid;
    }

    public void setTopicid(Integer topicid) {
        this.topicid = topicid;
    }
}
