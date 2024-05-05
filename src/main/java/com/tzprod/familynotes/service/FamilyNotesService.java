package com.tzprod.familynotes.service;

import com.tzprod.familynotes.exception.FamilyNotesException;
import com.tzprod.familynotes.model.Note;
import com.tzprod.familynotes.model.Topic;
import com.tzprod.familynotes.model.TopicsEntity;
import com.tzprod.familynotes.model.User;
import com.tzprod.familynotes.repostiory.FamilyNotesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@Service
public class FamilyNotesService {
    private final FamilyNotesRepository familyNotesRepository;
    public FamilyNotesService(FamilyNotesRepository familyNotesRepository) {
        this.familyNotesRepository = familyNotesRepository;
    }

    public List<Topic> getAllTopics() {
        return familyNotesRepository.getAllTopics();
    }

    public List<Topic> getTopicsByUser(Integer userId) {
        List<Topic> topics = new ArrayList<>();
        //First get the topic then loop to get the notes for each topic
       return  familyNotesRepository.getTopicsByUser(userId);
//        List<Note> notes = null;
//        for(TopicsEntity topicsEntity: entities ) {
//            log.info("Service topic: {}", topicsEntity.getTopicId());
//            notes = familyNotesRepository.getNotesByTopic(topicsEntity.getTopicId());
//            topics.add(new Topic(topicsEntity.getTopicId(),
//                                 topicsEntity.getUserId(),
//                                 topicsEntity.getTopic(),
//                                 notes,
//                                 topicsEntity.getTime(),
//                                 topicsEntity.isSharable())
//            );

//        }
//        return topics;
    }

    public Topic addTopic(Topic topic) {
        List<Note> notes = familyNotesRepository.getNotesByTopic(topic.getTopicId());
        return familyNotesRepository.addTopic(topic);
    }

    public ResponseEntity<Topic> updateTopic(Topic topic) {
        return familyNotesRepository.updateTopic(topic);
    }

    public ResponseEntity<HttpStatus> deleteTopic(String topicId) {
        return familyNotesRepository.deleteTopic(topicId);
    }

    public List<Note> getNotesByTopic(Integer topicId) {
        return familyNotesRepository.getNotesByTopic(topicId);
    }

    /**
     * Determine which note constructor to use by which note values are present. 0 is both. 1 is note only. 2 is image only
     * @param note The Note to add
     * @return The Added Note
     * @throws FamilyNotesException runtime exception for any error
     */
    public Note addNote(Note note) throws FamilyNotesException {
        return familyNotesRepository.addNote(note);
    }

    public User getUserById(Integer userId) throws FamilyNotesException {
        Optional<User> user = Optional.ofNullable(familyNotesRepository.getUserById(userId));
        return user.orElse(null);
    }

    public User addUser(User user) {
        return familyNotesRepository.addUser(user);
    }
}
