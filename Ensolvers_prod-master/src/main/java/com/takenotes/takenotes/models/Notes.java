package com.takenotes.takenotes.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Entity
@Table(name = "table_notes")
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String title;
    private String description;
    private boolean archived;
    private String color;
    private boolean active;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "note", cascade = CascadeType.ALL)
    private Set<NotesCategories> notesCategoriesSet = new HashSet<>();

    public Notes() {
    }

    public Notes(String title, String description, boolean archived, String color, boolean active) {
        this.title = title;
        this.description = description;
        this.archived = archived;
        this.color = color;
        this.active = active;
    }
    // Getters

    public long getId() {return id;}
    public String getTitle() {return title;}
    public String getDescription() {return description;}
    public boolean isArchived() {return archived;}
    public String getColor() {return color;}
    public User getUser() {return user;}
    public Set<NotesCategories> getNotesCategoriesSet() {return notesCategoriesSet;}
    public boolean isActive() {return active;}
    // Setters

    public void setTitle(String title) {this.title = title;}
    public void setDescription(String description) {this.description = description;}
    public void setArchived(boolean archived) {this.archived = archived;}
    public void setColor(String color) {this.color = color;}
    public void setUser(User user) {this.user = user;}
    public void setActive(boolean active) {this.active = active;}

    public void setNotesCategoriesSet(Set<NotesCategories> notesCategoriesSet) {
        this.notesCategoriesSet = notesCategoriesSet;
    }

    public void addNotesCategories(NotesCategories notesCategories) {
        notesCategories.setNote(this);
        notesCategoriesSet.add(notesCategories);
    }


    public List<Categories> getCategories() {
        return notesCategoriesSet.stream().map(NotesCategories::getCategoriesSet).collect(toList());
    }
}