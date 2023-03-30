package com.team3dat3.backend.service;

import com.team3dat3.backend.dto.user.UserRequest;
import com.team3dat3.backend.dto.user.UserResponse;
import com.team3dat3.backend.entity.Movie;
import com.team3dat3.backend.entity.User;
import com.team3dat3.backend.repository.AchievementRepository;
import com.team3dat3.backend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
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

    public UserResponse register(UserRequest userRequest){
        User user = userRequest.toUser();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("MEMBER")); // Default
        user.setAccountNonExpired(true); // Default
        user.setAccountNonLocked(true); // Default
        user.setCredentialsNonExpired(true); // Default
        user.setEnabled(true); // Default
        user = userRepository.save(user);
        return new UserResponse(user, true);
    }

    public UserResponse update(UserRequest userRequest){
        User user = userRepository
                .findById(userRequest.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        String password = user.getPassword();
        userRequest.copyTo(user);
        user.setPassword(password); // Don't update password
        return new UserResponse(userRepository.save(user), true);
    }
 
    public UserResponse updateAuthenticatedUser(UserRequest userRequest){
        User user = userRepository
                .findById(userRequest.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<String> roles = user.getRoles();
        boolean isAccountNonExpired = user.isAccountNonExpired();
        boolean isAccountNonLocked = user.isAccountNonLocked();
        boolean isCredentialsNonExpired = user.isCredentialsNonExpired();
        boolean isEnabled = user.isEnabled();
        userRequest.copyTo(user);
        user.setRoles(roles); // Don't update roles
        user.setAccountNonExpired(isAccountNonExpired); // Don't update accountNonExpired
        user.setAccountNonLocked(isAccountNonLocked); // Don't update accountNonLocked
        user.setCredentialsNonExpired(isCredentialsNonExpired); // Don't update credentialsNonExpired
        user.setEnabled(isEnabled); // Don't update enabled
        return new UserResponse(userRepository.save(user), true);
    }

    public void delete(UserRequest userRequest){
        User user = userRepository
                .findById(userRequest.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        userRepository.delete(user);
    }
}
