package org.productivity.controllers;

import org.productivity.domain.NoteService;
import org.productivity.domain.Result;
import org.productivity.models.Note;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

@RestController
//@CrossOrigin(origins = {"http://localhost:8080"})
@RequestMapping("/dashboard/noteWidget/note")
public class NoteController {

    private final NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

    @GetMapping
    public List<Note> findAll() {
        return service.findAll();
    }

    @GetMapping("/byDate/{date}")
    public ResponseEntity<List<Note>> findByNoteDate(@PathVariable("date") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date) {
        List<Note> notes = service.findByNoteDate(date);

        if(date == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(notes);
    }

    @GetMapping("/byId/{noteId}")
    public ResponseEntity<Note> findByNoteId(@PathVariable int noteId){
        Note note = service.findByNoteId(noteId);

        if (note == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(note);
    }

    @GetMapping("/byDescription/{description}")
    public ResponseEntity<List<Note>> findByNoteDescription(@PathVariable String description) {
        List<Note> notes = service.findByNoteDescription(description);

        if (description == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(notes);
    }

    @PostMapping
    public ResponseEntity<Object> createNote(@RequestBody Note note) {
        Result<Note> result = service.createNote(note);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }

        return ErrorResponse.build(result);
    }

    @PutMapping("/{noteId}")
    public ResponseEntity<Object> updateNote(@PathVariable int noteId, @RequestBody Note note) {
        if (noteId != note.getNoteId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Note> result = service.updateNote(note);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<Void> deleteNoteById(@PathVariable int noteId) {
        if (service.deleteNoteById(noteId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
