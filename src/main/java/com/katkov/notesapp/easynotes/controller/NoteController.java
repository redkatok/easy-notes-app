package com.katkov.notesapp.easynotes.controller;

import com.katkov.notesapp.easynotes.entities.Note;
import com.katkov.notesapp.easynotes.exception.ResourceNotFoundException;
import com.katkov.notesapp.easynotes.repository.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    private NoteRepo repository;

    @GetMapping
    public List<Note> getAllNotes() {
        return repository.findAll();
    }

    @PostMapping("/notes")
    public Note createNNote(@Valid @RequestBody Note note) {
        return repository.save(note);
    }

    @GetMapping("/note/{id}")
    public Note getNoteById(@PathVariable(value = "id") Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));

    }

    @PutMapping(value = "/notes/{id}")
    public Note updateNote(@PathVariable(value = "id") Long id, @Valid @RequestBody Note noteDetails) {
        Note note = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());
        Note updatedNote = repository.save(note);
        return updatedNote;
    }


    @DeleteMapping("notes/{id}")


    public ResponseEntity<?> deletNote(@PathVariable("id") Long id) {
        Note note = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
        repository.delete(note);
        return ResponseEntity.ok().build();

    }

}
