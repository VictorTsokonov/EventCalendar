package com.example.usersmicroservice.Repositories;

import com.example.usersmicroservice.Entities.NoteEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository {

    NoteEntity createNote(NoteEntity noteEntity);

    Optional<NoteEntity> getNote(long id);

    NoteEntity updateNote(NoteEntity noteEntity);

    void deleteNote(String id);

    List<NoteEntity> getAllNotesByUserId(String userId);
}