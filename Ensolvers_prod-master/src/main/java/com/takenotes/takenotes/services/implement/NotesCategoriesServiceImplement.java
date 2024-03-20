package com.takenotes.takenotes.services.implement;

import com.takenotes.takenotes.dto.NotesCategoriesDTO;
import com.takenotes.takenotes.models.NotesCategories;
import com.takenotes.takenotes.repositories.NotesCategoriesRepository;
import com.takenotes.takenotes.services.CategoriesService;
import com.takenotes.takenotes.services.NotesCategoriesService;
import com.takenotes.takenotes.services.NotesService;
import com.takenotes.takenotes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesCategoriesServiceImplement implements NotesCategoriesService {
    @Autowired
    NotesCategoriesRepository notesCategoriesRepository;
    @Autowired
    UserService userService;
    @Autowired
    NotesService notesService;
    @Autowired
    CategoriesService categoriesService;

    @Override
    public void saveNotesCategories(NotesCategories notesCategories){
        notesCategoriesRepository.save(notesCategories);
    }
    @Override
    public NotesCategories getNotesCategories(Long idNotes) {
        return notesCategoriesRepository.findById(idNotes).orElse(null);
    }

    @Override
    public void deleteNoteCategorie(Long id){
        notesCategoriesRepository.deleteById(id);
    }
}