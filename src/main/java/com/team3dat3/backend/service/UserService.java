package com.team3dat3.backend.service;

import com.team3dat3.backend.dto.user.UserRequest;
import com.team3dat3.backend.dto.user.UserResponse;
import com.team3dat3.backend.entity.User;
import com.team3dat3.backend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public List<UserResponse> findAll() {
        return userRepository
                .findAll()
                .stream()
                .map(u -> new UserResponse(u, true))
                .collect(Collectors.toList());
    }

    public UserResponse find(int id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new UserResponse(user, true);
    }

    public UserResponse create(UserRequest userRequest){
        User user = userRepository.save(
                userRequest.toUser()
        );
        return new UserResponse(user, true);
    }

    public UserResponse update(UserRequest userRequest){
        User user = userRepository
                .findById(userRequest.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        userRequest.copyTo(user);
        return new UserResponse(userRepository.save(user), true);
    }

    public void delete(UserRequest userRequest){
        User user = userRepository
                .findById(userRequest.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        userRepository.delete(user);
    }
}
