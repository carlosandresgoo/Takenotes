package com.takenotes.takenotes.controllers;

import com.takenotes.takenotes.dto.NotesDTO;
import com.takenotes.takenotes.models.Categories;
import com.takenotes.takenotes.models.Notes;
import com.takenotes.takenotes.models.User;
import com.takenotes.takenotes.models.NotesCategories;
import com.takenotes.takenotes.services.CategoriesService;
import com.takenotes.takenotes.services.NotesCategoriesService;
import com.takenotes.takenotes.services.NotesService;
import com.takenotes.takenotes.services.UserService;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class NotesController {
    @Autowired
    UserService userService;
    @Autowired
    CategoriesService categoriesService;
    @Autowired
    NotesService notesService;
    @Autowired
    NotesCategoriesService notesCategoriesService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/api/users/notes")
    public List<NotesDTO> getNotes() {
        return notesService.getNotes();
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/api/users/current/notes")
    public List<NotesDTO> getNotesCurrent(Authentication authentication) {
        return notesService.getNotesCurrent(authentication) ;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/api/notes")
    public ResponseEntity<Object> createNotes
            (Authentication authentication,
             @RequestParam String title,
             @RequestParam String description,
             @RequestParam (required = false)boolean archived,
             @RequestParam String color) {

        User user = userService.findByEmail(authentication.getName());
        if (user == null) {
            return new ResponseEntity<>("You can't create an notes because you're not a user.", HttpStatus.NOT_FOUND);
        }

        Notes newNotes = new Notes(title, description, false, color, true);
        user.addNotes(newNotes);
        notesService.saveNotes(newNotes);

        return new ResponseEntity<>(newNotes.getId(),HttpStatus.CREATED);

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping ("/api/notes")
    public ResponseEntity<Object> updateNotes
            (Authentication authentication,
             @RequestParam Long id,
             @RequestParam String title,
             @RequestParam String description,
             @RequestParam (required = false)boolean archived,
             @RequestParam String color){

        Notes notes = notesService.findById(id);

        if (!notes.getTitle().equalsIgnoreCase(title) ||
            !notes.getDescription().equalsIgnoreCase(description) ||
            !notes.getColor().equalsIgnoreCase(color)
        ) {
            notes.setTitle(title);
            notes.setDescription(description);
            notes.setColor(color);
            notesService.saveNotes(notes);
            return new ResponseEntity<>(HttpStatus.OK);
        } return new ResponseEntity
                <>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/api/notesCategories")
    public ResponseEntity<Object> createNotesCategories
            (
             @RequestParam Long notesId,
             @RequestParam String name,
             @RequestParam String color,
             @RequestParam(required = false) boolean checked) {

        Notes notes = notesService.findById(notesId);

        NotesCategories newNotesCategories = new NotesCategories(name, color, true);
        notesCategoriesService.saveNotesCategories(newNotesCategories);
        notes.addNotesCategories(newNotesCategories);
        notesService.saveNotes(notes);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/api/notesCategories")
    public ResponseEntity<Object> deleteNotesCategories
            (@RequestParam Long id) {

        notesCategoriesService.deleteNoteCategorie(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping ("/api/notes/archived")
    public ResponseEntity<Object> updateNotes
            ( @RequestParam Long id,
              @RequestParam boolean archived) {

        Notes notes = notesService.findById(id);

        if ( !notes.isArchived() == archived ){
            notes.setArchived(archived);
            notesService.saveNotes(notes);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping ("/api/notes/inactive/{id}")
    public ResponseEntity<Object> deleteNote
            (@PathVariable Long id) {

        Notes notes = notesService.findById(id);
        notes.setActive(false);
        notesService.saveNotes(notes);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}