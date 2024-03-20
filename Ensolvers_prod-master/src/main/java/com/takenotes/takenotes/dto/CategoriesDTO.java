package com.takenotes.takenotes.dto;

import com.takenotes.takenotes.models.Categories;

public class CategoriesDTO {
    private long id;
    private String name;
    private String color;

    public CategoriesDTO(Categories categories){
        this.id = categories.getId();
        this.name = categories.getName();
        this.color = categories.getColor();
    }

    public long getId() {return id;}
    public String getName() {return name;}
    public String getColor() {return color;}
}
