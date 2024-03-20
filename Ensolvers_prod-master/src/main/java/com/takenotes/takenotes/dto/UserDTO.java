package com.takenotes.takenotes.dto;

import com.takenotes.takenotes.models.User;

import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

public class UserDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<NotesDTO> notes;
    private Set<CategoriesDTO> categoriesDTOSet;

    public UserDTO(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.notes = user.getNotes().stream().map(notes -> new NotesDTO(notes)).collect(toSet());
        this.categoriesDTOSet = user.getCategoriesSet().stream().map(CategoriesDTO::new).collect(toSet());
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    public String getEmail() {
        return email;
    }

    public Set<NotesDTO> getNotes() {
        return notes;
    }

    public Set<CategoriesDTO> getCategoriesDTOSet() {
        return categoriesDTOSet;
    }
}