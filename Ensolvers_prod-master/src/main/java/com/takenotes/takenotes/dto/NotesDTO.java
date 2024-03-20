package com.takenotes.takenotes.dto;

import com.takenotes.takenotes.models.Notes;

import java.util.Set;
import java.util.stream.Collectors;

public class NotesDTO {

    private long id;
    private String title;
    private String description;
    private boolean archived;
    private String color;
    private boolean active;
    private Set<NotesCategoriesDTO> notesCategoriesDTOSet;
    public NotesDTO(Notes notes) {
        this.id = notes.getId();
        this.title = notes.getTitle();
        this.description = notes.getDescription();
        this.archived = notes.isArchived();
        this.color = notes.getColor();
        this.active = notes.isActive();
        this.notesCategoriesDTOSet = notes.getNotesCategoriesSet().stream().map(notesCategories -> new NotesCategoriesDTO(notesCategories)).collect(Collectors.toSet());
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isArchived() {
        return archived;
    }

    public String getColor() {
        return color;
    }

    public boolean isActive() {
        return active;
    }

    public Set<NotesCategoriesDTO> getNotesCategoriesDTOSet() {
        return notesCategoriesDTOSet;
    }
}