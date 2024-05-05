package com.tzprod.familynotes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "notes", schema = "familynotes")
public class NotesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idnote", nullable = false)
    private int idnote;
    @Basic
    @Column(name = "title", nullable = true, length = 100)
    private String title;
    @Basic
    @Column(name = "text", nullable = true, length = 1000)
    private String text;
    @Basic
    @Column(name = "image", nullable = true)
    private byte[] image;
    @Basic
    @Column(name = "time", nullable = true)
    private Timestamp time;
    @Basic
    @Column(name = "userid", nullable = false)
    private int userid;
    @Basic
    @Column(name = "topicid", nullable = false)
    private int topicid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotesEntity that = (NotesEntity) o;
        return idnote == that.idnote && userid == that.userid && topicid == that.topicid && Objects.equals(title, that.title) && Objects.equals(text, that.text) && Arrays.equals(image, that.image) && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(idnote, title, text, time, userid, topicid);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }
}