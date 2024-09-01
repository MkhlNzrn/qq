package com.example.demo.services;

import com.example.demo.dto.UserDto;
import com.example.demo.entities.User;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User authenticateUser(UserDto userDto) throws UsernameNotFoundException {
        UserDetails userDetails = loadUserByUsername(userDto.username());
        return (bCryptPasswordEncoder.matches(userDto.password(), userDetails.getPassword()))
                ? (User) userDetails
                : null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User %s not found".formatted(username));
        }

        return user;
    }

    public boolean saveUser(UserDto userDto) {
        User userFromDB = userRepository.findByUsername(userDto.username());
        if (userFromDB != null) {
            return false;
        }


        User user = new User();
        user.setUsername(userDto.username());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.password()));
        user.setRoles(Collections.singleton(roleRepository.findByName("ROLE_USER")));
        userRepository.save(user);
        return true;
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }

        return false;
    }
}