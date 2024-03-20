package com.takenotes.takenotes.controllers;

import com.takenotes.takenotes.dto.UserDTO;
import com.takenotes.takenotes.models.User;
import com.takenotes.takenotes.repositories.UserRepository;
import com.takenotes.takenotes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/api/users")
    public List<UserDTO> getUser() {return userService.getUser();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/api/users/current")
    public UserDTO getUsers(Authentication authentication) {
        return userService.getUsers(authentication);
    }

    @PostMapping("/api/users")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> register(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String password) {

        if (firstName.isBlank()) {
            return new ResponseEntity<>("Please complete your firstName on the form.", HttpStatus.FORBIDDEN);
        } else if (!firstName.matches("^[a-zA-Z]*$")) {
            return new ResponseEntity<>("Please enter a valid firstName. Only letters are allowed.", HttpStatus.FORBIDDEN);
        }

        if (lastName.isBlank()) {
            return new ResponseEntity<>("Please complete your lastName on the form.", HttpStatus.FORBIDDEN);
        } else if (!lastName.matches("^[a-zA-Z]*$")) {
            return new ResponseEntity<>("Please enter a valid lastName. Only letters are allowed.", HttpStatus.FORBIDDEN);
        }

        if (email.isBlank()) {
            return new ResponseEntity<>("Please complete your email on the form.", HttpStatus.FORBIDDEN);
        } else if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            return new ResponseEntity<>("Please enter a valid email address.", HttpStatus.FORBIDDEN);
        }

        if (password.isBlank()) {
            return new ResponseEntity<> ("Please complete your password on the form.", HttpStatus.FORBIDDEN);
        }

        if (userService.findByEmail(email) !=  null) {
            return new ResponseEntity<>("Email already in use", HttpStatus.FORBIDDEN);
        }

        User newUser = new User(firstName, lastName, email, passwordEncoder.encode(password));
        userService.saveUser(newUser);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }

}