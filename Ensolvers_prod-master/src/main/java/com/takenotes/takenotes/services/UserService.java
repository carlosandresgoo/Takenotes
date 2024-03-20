package com.takenotes.takenotes.services;

import com.takenotes.takenotes.dto.UserDTO;
import com.takenotes.takenotes.models.User;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {
    List<UserDTO> getUser();

    UserDTO getUsers(Authentication authentication);

    User findByEmail(String email);


    void saveUser (User user);

    User getUserAuthenticated(Authentication authentication);
}