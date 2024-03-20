package com.takenotes.takenotes.services;

import com.takenotes.takenotes.dto.NotesDTO;
import com.takenotes.takenotes.models.Notes;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface NotesService {
    List<NotesDTO> getNotes();
    List<NotesDTO> getNotesCurrent(Authentication authentication);
    void saveNotes(Notes notes);
    NotesDTO getNotesDT0(Long id);
    Notes findById(Long id);

    void deleteNote(Long id);
}