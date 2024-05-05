package com.tzprod.familynotes.repostiory;

import com.tzprod.familynotes.exception.FamilyNotesException;
import com.tzprod.familynotes.model.Note;
import com.tzprod.familynotes.model.Topic;
import com.tzprod.familynotes.model.TopicsEntity;
import com.tzprod.familynotes.model.User;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
@NoArgsConstructor
public class FamilyNotesRepository {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private static final String ALL_TOPICS = """
                                                SELECT * FROM topics t
                                                WHERE t.userid = :userId
                                            """;
    private static final String TOPICS_BY_ID = """
                                                SELECT * FROM topics t
                                                INNER JOIN notes n ON n.topicid = t.idTopic
                                                WHERE idtopic = :topicId
                                            """;
    private static final String TOPICS_BY_USER = """
                                                    SELECT * FROM topics t
                                                    WHERE t.userid = :userId
                                                    
                                                """;
    private static final String INSERT_TOPIC = """
                                                INSERT INTO topics (userid, topic, sharable) VALUES
                                                 (:userId, :topic, :sharable)
                                               """;
    private static final String DELETE_TOPIC = "DELETE  topic WHERE idtopic = :topicId ";

    private static final String NOTES_BY_TOPIC = """
                                                    SELECT * FROM notes t
                                                    WHERE t.topicid = :topicId
                                                """;
    private static final String ADD_NOTE = """
                                            INSERT INTO notes (title, text, image, userid, topicid)
                                            VALUES(:title, :text, :image, :userid, :topicid)
                                           """;
    private static final String INSERT_TEXT_NOTE = "INSERT INTO notes (title, note, userid, topicid) VALUES " +
            " (:title, :note, :image, :userid. :topicId)";
    private static final String INSERT_IMAGE_NOTE = "INSERT INTO notes (title, image, userid, topicid) VALUES " +
            " (:title, :note, :image, :userid. :topicId)";
    private static final String USER_BY_ID = "SELECT * from users WHERE iduser = :userid";
    private static final String ADD_USER = "INSERT INTO users (firstname, lastname, alias, password) VALUES " +
            " (:firstname, :lastname, :alias, :password)";

    public List<Topic> getAllTopics() {
        SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("userId", 1);
        try {
            return jdbcTemplate.query(ALL_TOPICS, parameters, (rs, rowNum) -> new Topic(
                    rs.getInt("idTopic"),
                    rs.getInt("userId"),
                    rs.getString("topic"),
                    getNotesByTopic(rs.getInt("idTopic")),
                    rs.getObject("time", LocalDateTime.class),
                    rs.getBoolean("sharable")));
        } catch (EmptyResultDataAccessException ex) {
            log.error("error retrieving User", ex);
            throw new FamilyNotesException("error retrieving User");
        }
    }

    public List<Topic> getTopicsByUser(Integer userId) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("userId", userId);
        try {
            return jdbcTemplate.query(TOPICS_BY_USER, parameters, (rs, rowNum) ->
                    new Topic(
                        rs.getInt("idTopic"),
                        rs.getInt("userId"),
                        rs.getString("topic"),
                        getNotesByTopic(rs.getInt("idTopic")),
                        rs.getObject("time", LocalDateTime.class),
                        rs.getBoolean("sharable"))
            );
        } catch (EmptyResultDataAccessException ex) {
            log.error("error retrieving User", ex);
            throw  new FamilyNotesException("error retrieving User");
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public Topic addTopic(Topic topic) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("userId", topic.getUserId())
                .addValue("topic", topic.getTopic())
                .addValue("sharable", topic.isSharable());
        int result = jdbcTemplate.update(INSERT_TOPIC, parameters);
        if (result != 1) {
            throw new FamilyNotesException("Topic not added");
        }

        log.info("Topic Successfully added");
        return topic;
    }

    public ResponseEntity<Topic> updateTopic(Topic topic) {
        return new ResponseEntity<Topic>(topic, HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> deleteTopic(String topicId) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("topicid", topicId);
        ResponseEntity<HttpStatus> response = null;
        int retval = jdbcTemplate.update(DELETE_TOPIC, parameters);
        if (retval == 1) {
            response = new ResponseEntity<>(HttpStatus.OK);
            log.info("topic of id {} deleted", topicId);
        } else {
            new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return response;
    }

    public List<Note> getNotesByTopic(Integer topicId) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("topicId", topicId);
        log.info("Topic: {}", topicId);
        try {
            return jdbcTemplate.query(NOTES_BY_TOPIC, parameters, (rs, rowNum) ->
                    new Note(
                            rs.getInt("idnote"),
                            rs.getString("title"),
                            rs.getString("text"),
                            rs.getBytes("image"),
                            rs.getObject("time", LocalDateTime.class),
                            rs.getInt("userid"),
                            rs.getInt("topicid"))
            );
        } catch (EmptyResultDataAccessException ex) {
            log.error("error retrieving User", ex);
            throw  new FamilyNotesException("error retrieving User");
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public Note addNote(Note note) {
        int result = 0;
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("title", note.getTitle())
                .addValue("text", note.getText())
                .addValue("image", note.getNoteImage())
                .addValue("userid", note.getUserId())
                .addValue("topicid", note.getTopicId());
        result = jdbcTemplate.update(ADD_NOTE, parameters);
        if (result != 1) {
            throw new FamilyNotesException("Note not added");
        }
        log.info("Note Successfully added");
        return note;
    }
    public User getUserById(Integer userId) throws FamilyNotesException {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("userid", userId);
        try {
            return jdbcTemplate.queryForObject(USER_BY_ID, parameters, (rs, rowNum) ->
                    new User(
                            rs.getInt("iduser"),
                            rs.getString("firstname"),
                            rs.getString("lastname"),
                            rs.getString("alias"),
                            rs.getString("password"),
                            rs.getObject("time", LocalDateTime.class)));
        } catch (EmptyResultDataAccessException ex) {
            log.error("error retrieving User", ex);
            throw  new FamilyNotesException("error retrieving User");
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public User addUser(User user) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("firstname", user.getFirstName())
                .addValue("lastname", user.getLastName())
                .addValue("alias", user.getAlias())
                .addValue("password", user.getPassword());
        int result = jdbcTemplate.update(ADD_USER, parameters);
        if (result != 1) {
            throw new FamilyNotesException("User not added");
        }
        log.info("User Successfully added");
        return user;
    }

}
