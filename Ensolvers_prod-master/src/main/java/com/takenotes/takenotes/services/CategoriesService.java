package com.takenotes.takenotes.services;

import com.takenotes.takenotes.dto.CategoriesDTO;
import com.takenotes.takenotes.dto.UserDTO;
import com.takenotes.takenotes.models.Categories;

import java.util.List;

public interface CategoriesService {
    List<CategoriesDTO> getCategoriesDTO();
    void saveCategories(Categories categories);
    Categories findById(Long id);
    CategoriesDTO getCategoriesDT0(Long id);

    void deleteCategorie(Long id);
}