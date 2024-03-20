package com.takenotes.takenotes.repositories;

import com.takenotes.takenotes.models.NotesCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface NotesCategoriesRepository extends JpaRepository<NotesCategories, Long> {
}