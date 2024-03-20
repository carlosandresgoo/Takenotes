package com.takenotes.takenotes.services.implement;

import com.takenotes.takenotes.dto.CategoriesDTO;
import com.takenotes.takenotes.dto.UserDTO;
import com.takenotes.takenotes.models.Categories;
import com.takenotes.takenotes.repositories.CategoriesRepository;
import com.takenotes.takenotes.repositories.UserRepository;
import com.takenotes.takenotes.services.CategoriesService;
import com.takenotes.takenotes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class CategoriesServiceImplement implements CategoriesService {
    @Autowired
    private CategoriesRepository categoriesRepository;
    @Autowired
    UserService userService;

    @Override
    public List<CategoriesDTO> getCategoriesDTO() {
        return categoriesRepository.findAll().stream().map(CategoriesDTO::new).collect(toList());
    }
    @Override
    public void saveCategories(Categories categories) {categoriesRepository.save(categories);}
    @Override
    public Categories findById(Long id) {return categoriesRepository.findById(id).orElse(null);
    }

    @Override
    public CategoriesDTO getCategoriesDT0(Long id) {
        return categoriesRepository.findById(id).map(categories -> new CategoriesDTO(categories)).orElse(null);
    }

    @Override
    public void deleteCategorie(Long id) {
        categoriesRepository.deleteById(id);
    }
}