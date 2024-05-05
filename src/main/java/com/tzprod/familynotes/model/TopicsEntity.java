package com.tzprod.familynotes.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "topics", schema = "familynotes")
@AllArgsConstructor
@NoArgsConstructor
public class TopicsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int topicId;
    private int userId;
    private String topic;
    private boolean isSharable;
    @Nullable
    private LocalDateTime time;

}
