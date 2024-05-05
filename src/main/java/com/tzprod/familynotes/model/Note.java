package com.tzprod.familynotes.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity(name = "Note")
@NoArgsConstructor
@AllArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int noteId;
    @Schema(
            description = "Note Title",
            name = "title",
            maxLength = 100,
            example = "Passwords")
    @NotNull
    private String title;
    @Schema(
            description = "The Note text",
            name = "text",
            maxLength = 1000,
            example = "User: MyUser\r\n Password: MyPassword \r\n")
    @Nullable
    private String text;
    @Schema(
            description = "The Note Image",
            name = "image")
    @Nullable
    @Lob
    private byte[] noteImage;
    @Nullable
    private LocalDateTime time;
    @Schema(
            description = "User ID for Note",
            name = "userId",
            maxLength = 100,
            example = "1")
    private int userId;
    @Schema(
            description = "Topic ID fro Note",
            name = "topicId",
            maxLength = 100,
            example = "3")
    private int topicId;

    public Note(String title, String text, byte[] image, Integer userId, Integer topicId) {
        this.title = title;
        this.text = text;
        this.noteImage = image;
        this.userId = userId;
        this.topicId = topicId;
    }
}
