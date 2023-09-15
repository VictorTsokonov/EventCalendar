package com.example.usersmicroservice.Entities;

public record NoteEntity(long id, long userId, String eventName, String text) {
}
