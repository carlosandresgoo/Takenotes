package com.takenotes.takenotes.repositories;

import com.takenotes.takenotes.models.Notes;
import com.takenotes.takenotes.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface NotesRepository extends JpaRepository<Notes, Long> {
    Notes findById (long id);
}