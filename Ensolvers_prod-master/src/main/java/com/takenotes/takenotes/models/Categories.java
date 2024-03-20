package com.takenotes.takenotes.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Entity
@Table(name = "table_categories")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String name;
    private String color;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @OneToMany(mappedBy = "categoriesSet", cascade = CascadeType.ALL)
    private Set<NotesCategories> notesCategoriesSet = new HashSet<>();

    public Categories() {}

    public Categories(String name, String color){
        this.name = name;
        this.color = color;
    }

    // GETTER
    public long getId() {return id;}
    public String getName() {return name;}
    public String getColor() {return color;}
    public User getUser() {return user;}
    public Set<NotesCategories> getNotesCategoriesSet() {
        return notesCategoriesSet;
    }

    // SETTERS
    public void setId(long id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setColor(String color) {this.color = color;}
    public void setUser(User user) {this.user = user;}
    public void setNotesCategoriesSet(Set<NotesCategories> notesCategoriesSet) {
        this.notesCategoriesSet = notesCategoriesSet;
    }

    // Método para agregar una relación NotesCategories
    public void addNotesCategories(NotesCategories notesCategories) {
        notesCategories.setCategoriesSet(this);
        notesCategoriesSet.add(notesCategories);
    }

    // Método para obtener las notas asociadas a esta categoría
    public List<Notes> getNotes() {
        return notesCategoriesSet.stream().map(NotesCategories::getNote).collect(toList());
    }
}