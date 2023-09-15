package com.example.usersmicroservice.Controllers;

import com.example.usersmicroservice.Entities.NoteEntity;
import com.example.usersmicroservice.Services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public ResponseEntity<NoteEntity> createNote(@RequestBody NoteEntity noteEntity) {
        NoteEntity createdNote = noteService.createNote(noteEntity);
        return new ResponseEntity<>(createdNote, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteEntity> getNote(@PathVariable long id) {
        return noteService.getNote(id)
                .map(noteEntity -> new ResponseEntity<>(noteEntity, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NoteEntity>> getNotesByUserId(@PathVariable String userId) {
        List<NoteEntity> notes = noteService.getAllNotesByUserId(userId);
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteNoteByUserIdAndId(@RequestParam long userId, @RequestParam long id) {
        noteService.deleteNoteByUserIdAndId(userId, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // ... (implement other endpoints like update, delete, etc.) ...
}
