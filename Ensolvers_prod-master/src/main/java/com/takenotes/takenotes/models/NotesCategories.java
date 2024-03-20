package com.takenotes.takenotes.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "notes_categories")
public class NotesCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String name;
    private String color;
    private boolean checked;

    @ManyToOne
    @JoinColumn(name = "note_id")
    private Notes note;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categories categoriesSet;

    public NotesCategories() {
    }

    public NotesCategories(String name, String color, boolean checked) {
        this.name = name;
        this.color = color;
        this.checked = checked;
    }

    //Getter

    public long getId() {return id;}
    public boolean isChecked() {return checked;}
    public Notes getNote() {return note;}
    public Categories getCategoriesSet() {return categoriesSet;}
    public String getName() {return name;}
    public String getColor() {return color;}

    // Setter
    public void setChecked(boolean checked) {this.checked = checked;}
    public void setNote(Notes note) {this.note = note;}
    public void setCategoriesSet(Categories categoriesSet) {this.categoriesSet = categoriesSet;}
    public void setName(String name) {this.name = name;}
    public void setColor(String color) {this.color = color;}

}