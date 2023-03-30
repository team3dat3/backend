package com.team3dat3.backend.api.v1;

import com.team3dat3.backend.dto.user.UserRequest;
import com.team3dat3.backend.dto.user.UserResponse;
import com.team3dat3.backend.service.UserService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1")
@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/users")
    public List<UserResponse> findAll() {
        return userService.findAll();
    }

    @GetMapping("/admin/users/{username}")
    public UserResponse find(@PathVariable("username") String username) {
        return userService.find(username);
    }

    @PostMapping("/admin/users")
    public UserResponse create(@RequestBody UserRequest userRequest) {
        return userService.create(userRequest);
    }

    @PutMapping("/admin/users")
    public UserResponse update(@RequestBody UserRequest userRequest) {
        return userService.update(userRequest);
    }

    @DeleteMapping("/admin/users")
    public void delete(@RequestBody UserRequest userRequest){
        userService.delete(userRequest);
    }

    @PostMapping("/register")
    public UserResponse register(@RequestBody UserRequest userRequest) {
        return userService.register(userRequest);
    } 

    @GetMapping("/member/users")
    public UserResponse findAuthenticatedUser(@AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getSubject();
        return userService.find(username);
    }

    @PutMapping("/member/users")
    public UserResponse updateAuthenticatedUser(@AuthenticationPrincipal Jwt jwt, @RequestBody UserRequest userRequest) {
        String username = jwt.getSubject();
        userRequest.setUsername(username); // Set username to the one in the JWT
        return userService.updateAuthenticatedUser(userRequest);
    }
}
