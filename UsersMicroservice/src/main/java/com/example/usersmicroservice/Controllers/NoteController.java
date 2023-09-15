package com.example.usersmicroservice.Controllers;

import com.example.usersmicroservice.Entities.NoteEntity;
import com.example.usersmicroservice.Services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // ... (implement other endpoints like update, delete, etc.) ...
}
