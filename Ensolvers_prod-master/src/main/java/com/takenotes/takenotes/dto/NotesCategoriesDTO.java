package com.takenotes.takenotes.dto;

import com.takenotes.takenotes.models.NotesCategories;

public class NotesCategoriesDTO {
    private long id;
    private long notesId;
    private String name;
    private String color;
    private boolean checked;

    public  NotesCategoriesDTO (NotesCategories notesCategories){
        this.id = notesCategories.getId();
        this.notesId  = notesCategories.getNote().getId();
        this.name = notesCategories.getName();
        this.color = notesCategories.getColor();
        this.checked = notesCategories.isChecked();
    }


    public long getId() {return id;}
    public long getNotesId() {return notesId;}
    public String getName() {return name;}
    public String getColor() {return color;}
    public boolean isChecked() {return checked;}
}