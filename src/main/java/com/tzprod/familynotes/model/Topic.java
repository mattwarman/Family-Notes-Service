package com.tzprod.familynotes.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Topic {
    private int topicId;
    private int userId;
    private String topic;
    private List<Note> notes;
    private LocalDateTime time;
    private boolean isSharable;

}
