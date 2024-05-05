package com.tzprod.familynotes.controller;

import com.tzprod.familynotes.model.Note;
import com.tzprod.familynotes.model.Topic;
import com.tzprod.familynotes.model.User;
import com.tzprod.familynotes.service.FamilyNotesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@Component
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class FamilyNotesController {
    private static final String OK = "200";
    private static final String ERROR = "400";
    private static final String NOT_FOUND = "404";
    
    private final FamilyNotesService familyNotesService;

    public FamilyNotesController(FamilyNotesService familyNotesService) {
        this.familyNotesService = familyNotesService;
    }

    @Operation(summary = "Retrieves all Topics.",
            responses = { @ApiResponse(responseCode = OK, description = "Topics Returned",
                                       content = @Content(schema = @Schema(implementation = List.class))),
                          @ApiResponse(responseCode = ERROR, description = "Topics Error"),
                          @ApiResponse(responseCode = NOT_FOUND, description = "Topic not found") })
    @GetMapping("/topic")
    public List<Topic> getAllTopics() {
        log.info("getting all Topics");
        List<Topic> topics =  familyNotesService.getAllTopics();
        topics.forEach(t -> {log.info("Topic: {}", t.toString());
          t.getNotes().forEach(n -> {log.info("Topic: {}", n.toString());});
        });
        return topics;
    }

    @Operation(summary = "Retrieves all Topics for a User.",
            responses = { @ApiResponse(responseCode = OK, description = "Topics Returned",
                    content = @Content(schema = @Schema(implementation = List.class))),
                    @ApiResponse(responseCode = NOT_FOUND, description = "Topic not found") })
    @GetMapping("/topic/{userId}")
    public List<Topic> getTopicsByUser(@PathVariable Integer userId) {
        return familyNotesService.getTopicsByUser(userId);
    }

    @PostMapping("/topic")
    public Topic addTopic(@RequestBody Topic topic) {
        return familyNotesService.addTopic(topic);
    }

    @PutMapping("/topic/{topic}")
    public ResponseEntity<Topic> updateTopic(@PathVariable Topic topic) {
        return familyNotesService.updateTopic(topic);
    }

    @DeleteMapping("/topic/{topicId}")
    public ResponseEntity<HttpStatus> deleteTopic(@PathVariable String topicId) {
        return familyNotesService.deleteTopic(topicId);
    }

    @Operation(summary = "Add A Note.",
            responses = { @ApiResponse(responseCode = OK, description = "Note Created",
                    content = @Content(schema = @Schema(implementation = Note.class))),
                    @ApiResponse(responseCode = NOT_FOUND, description = "Note not created") })
    @PostMapping("/note")
    public Note addNote(@RequestBody Note note) {
        return familyNotesService.addNote(note);
    }


    @Operation(summary = "Retrieves User by ID.",
            responses = { @ApiResponse(responseCode = OK, description = "User Returned",
                    content = @Content(schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = NOT_FOUND, description = "User not found") })
    @GetMapping("/user/{userId}")
    public User getUserById(@PathVariable Integer userId) {
        return familyNotesService.getUserById(userId);
    }

    @Operation(summary = "Add A User.",
            responses = { @ApiResponse(responseCode = OK, description = "User Created",
                    content = @Content(schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = NOT_FOUND, description = "User not created") })
    @PostMapping("/user")
    public User addUser(@RequestBody User user) {
        return familyNotesService.addUser(user);
    }

}
