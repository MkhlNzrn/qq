package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/registration_controller")
public class RegistrationController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Boolean> registerUser(@RequestBody UserDto userDto) {
        try {
            boolean isSaved = userService.saveUser(userDto);
            return ResponseEntity.ok(isSaved);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}