package com.team3dat3.backend.service;

import com.team3dat3.backend.dto.user.UserRequest;
import com.team3dat3.backend.dto.user.UserResponse;
import com.team3dat3.backend.entity.User;
import com.team3dat3.backend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public List<UserResponse> findAll() {
        return userRepository
                .findAll()
                .stream()
                .map(u -> new UserResponse(u, true))
                .collect(Collectors.toList());
    }

    public UserResponse find(String username) {
        User user = userRepository
                .findById(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new UserResponse(user, true);
    }

    public UserResponse create(UserRequest userRequest){
        User user = userRequest.toUser();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        return new UserResponse(user, true);
    }

    public UserResponse update(UserRequest userRequest){
        User user = userRepository
                .findById(userRequest.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        userRequest.copyTo(user);
        return new UserResponse(userRepository.save(user), true);
    }

    public void delete(UserRequest userRequest){
        User user = userRepository
                .findById(userRequest.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        userRepository.delete(user);
    }
}
