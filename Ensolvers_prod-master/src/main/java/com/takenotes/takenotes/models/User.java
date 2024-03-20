package com.takenotes.takenotes.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "table_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Notes> notes = new HashSet<>();
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Categories> categoriesSet = new HashSet<>();

    public User() {
    }

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    // Getter

    public long getId() {return id;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getEmail() {return email;}
    public String getPassword() {return password;}
    public Set<Notes> getNotes() {return notes;}
    public Set<Categories> getCategoriesSet() {return categoriesSet;}

    // Setter

    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public void setEmail(String email) {this.email = email;}
    public void setPassword(String password) {this.password = password;}
    public void setNotes(Set<Notes> notes) {this.notes = notes;}
    public void setCategoriesSet(Set<Categories> categoriesSet) {this.categoriesSet = categoriesSet;}


    public void addNotes(Notes notes) {
        notes.setUser(this);
        this.notes.add(notes);
    }
    public void addCategoria(Categories categories){
        categories.setUser(this);
        categoriesSet.add(categories);
    }

}