package com.takenotes.takenotes.services.implement;

import com.takenotes.takenotes.dto.UserDTO;
import com.takenotes.takenotes.models.User;
import com.takenotes.takenotes.repositories.UserRepository;
import com.takenotes.takenotes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class UserServiceImplement implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<UserDTO> getUser() {
        return userRepository.findAll().stream().map(user -> new UserDTO(user)).collect(toList());
    }

    @Override
    public UserDTO getUsers(Authentication authentication) {
        return  new UserDTO(userRepository.findByEmail(authentication.getName()));
    }
    @Override
    public User findByEmail(String email) {return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {userRepository.save(user);
    }

    @Override
    public User getUserAuthenticated(Authentication authentication) {
        return userRepository.findByEmail(authentication.getName());
    }
}