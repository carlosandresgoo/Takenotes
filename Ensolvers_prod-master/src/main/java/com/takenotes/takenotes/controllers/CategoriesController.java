package com.takenotes.takenotes.controllers;

import com.takenotes.takenotes.dto.CategoriesDTO;
import com.takenotes.takenotes.models.Categories;
import com.takenotes.takenotes.models.Notes;
import com.takenotes.takenotes.models.User;
import com.takenotes.takenotes.services.CategoriesService;
import com.takenotes.takenotes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@RestController
public class CategoriesController {
    @Autowired
    private UserService userService;
    @Autowired
    private CategoriesService categoriesService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/api/categories")
    public List<CategoriesDTO> getCategories() {return categoriesService.getCategoriesDTO();}

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/api/user/current/categories")
    public List<CategoriesDTO> getCategoriesByCurrentUser(Authentication authentication) {

        User user = userService.getUserAuthenticated(authentication);

        List<CategoriesDTO> categories = user.getCategoriesSet().stream().map(CategoriesDTO::new).collect(toList());
        return categories;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/api/categories")
    public ResponseEntity<Object> createCategories
            (Authentication authentication,
             @RequestParam String name,
             @RequestParam String color) {

        User user = userService.findByEmail(authentication.getName());
        if (user == null) {
            return new ResponseEntity<>("You can't create an categories because you're not a user.", HttpStatus.NOT_FOUND);
        }

        Categories newCategories = new Categories(name, color);
        user.addCategoria(newCategories);
        categoriesService.saveCategories(newCategories);

        return new ResponseEntity<>(newCategories.getId(),HttpStatus.CREATED);

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/api/categories/{id}")
    public ResponseEntity<Object> deleteCategories
            (@PathVariable Long id) {

        categoriesService.deleteCategorie(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}