package com.takenotes.takenotes.repositories;

import com.takenotes.takenotes.models.Categories;
import com.takenotes.takenotes.models.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CategoriesRepository extends JpaRepository<Categories, Long> {

    Categories findById (long id);

}