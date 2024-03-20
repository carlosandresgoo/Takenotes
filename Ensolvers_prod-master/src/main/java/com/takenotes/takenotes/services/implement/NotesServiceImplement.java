package com.takenotes.takenotes.services.implement;

import com.takenotes.takenotes.dto.NotesDTO;
import com.takenotes.takenotes.dto.UserDTO;
import com.takenotes.takenotes.models.Notes;
import com.takenotes.takenotes.repositories.NotesRepository;
import com.takenotes.takenotes.services.NotesService;
import com.takenotes.takenotes.services.UserService;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class NotesServiceImplement implements NotesService {
    @Autowired
    private NotesRepository notesRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<NotesDTO> getNotes() {
        return notesRepository.findAll().stream().map(NotesDTO::new).collect(toList());
    }

    @Override
    public List<NotesDTO> getNotesCurrent(Authentication authentication) {
        return new UserDTO(userService.findByEmail(authentication.getName())).getNotes().stream().collect(toList()) ;
    }

    @Override
    public void saveNotes(Notes notes) {
        notesRepository.save(notes);
    }

    @Override
    public NotesDTO getNotesDT0(Long id) {
        return notesRepository.findById(id).map(notes -> new NotesDTO(notes)).orElse(null);
    }

    @Override
    public Notes findById(Long id) {return notesRepository.findById(id).orElse(null);}

    @Override
    public void deleteNote(Long id) {notesRepository.deleteById(id);}


}