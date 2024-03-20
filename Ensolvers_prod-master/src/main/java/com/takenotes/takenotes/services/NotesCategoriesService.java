package com.takenotes.takenotes.services;

import com.takenotes.takenotes.dto.CategoriesDTO;
import com.takenotes.takenotes.dto.NotesCategoriesDTO;
import com.takenotes.takenotes.models.NotesCategories;

import java.util.List;

public interface NotesCategoriesService {

    void saveNotesCategories(NotesCategories notesCategories);

    NotesCategories getNotesCategories(Long idNotes);

    void deleteNoteCategorie(Long id);
}