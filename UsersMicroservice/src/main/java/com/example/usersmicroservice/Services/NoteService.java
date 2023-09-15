package com.example.usersmicroservice.Services;

import com.example.usersmicroservice.Entities.NoteEntity;
import com.example.usersmicroservice.Repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<NoteEntity> getAllNotesByUserId(String userId) {
        return noteRepository.getAllNotesByUserId(userId);
    }

    public void deleteNoteByUserIdAndId(long userId, long id) {
        noteRepository.deleteNoteByUserIdAndId(userId, id);
    }

    // ... (implement other methods like update, delete, etc.) ...
}
