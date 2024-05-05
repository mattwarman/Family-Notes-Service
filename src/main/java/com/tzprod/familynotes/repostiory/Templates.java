package com.tzprod.familynotes.repostiory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class Templates {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private static final String ALL_TOPICS = "SELECT * from topics";
    private static final String TOPICS_BY_USER = "SELECT * from topics WHERE userid = :userid";
    private static final String INSERT_TOPIC = "INSERT INTO address (street, city, state, zipcode) VALUES " +
            " (:street, :city, :state, :zipCode)";
    private static final String NOTES_BY_TOPIC = "SELECT * FROM notes WHERE topicid = :topicId ";
    private static final String INSERT_NOTE = "INSERT INTO notes (title, test, image, userid, topicid) VALUES " +
            " (:title, :text, :image, :userid. :topicId)";
    private static final String USER_BY_ID = "SELECT * from topics WHERE userid = :userid";
    private static final String ADD_USER = "INSERT INTO users (firstname, lastname, alias, password) VALUES " +
            " (:firstname, :lastname, :alias, :password)";


}
