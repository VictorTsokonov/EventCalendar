package com.example.usersmicroservice.Services;

import com.example.usersmicroservice.Entities.NoteEntity;
import com.example.usersmicroservice.Repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NoteService {


    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public NoteEntity createNote(NoteEntity noteEntity) {
        return noteRepository.createNote(noteEntity);
    }

    public Optional<NoteEntity> getNote(long id) {
        return noteRepository.getNote(id);
    }

    // ... (implement other methods like update, delete, etc.) ...
}
