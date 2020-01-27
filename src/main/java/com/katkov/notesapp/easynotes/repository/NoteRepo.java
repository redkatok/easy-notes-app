package com.katkov.notesapp.easynotes.repository;

import com.katkov.notesapp.easynotes.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepo extends JpaRepository<Note,Long> {
}
