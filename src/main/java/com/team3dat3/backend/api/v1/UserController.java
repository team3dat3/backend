package com.team3dat3.backend.api.v1;

import com.team3dat3.backend.dto.user.UserRequest;
import com.team3dat3.backend.dto.user.UserResponse;
import com.team3dat3.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1")
@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/anonymous/users")
    public List<UserResponse> findAll() {
        return userService.findAll();
    }

    @GetMapping("/anonymous/users/{username}")
    public UserResponse find(@PathVariable("username") String username) {
        return userService.find(username);
    }

    @PostMapping("/anonymous/users")
    public UserResponse create(@RequestBody UserRequest userRequest){
        return userService.create(userRequest);
    }

    @PatchMapping("/admin/users")
    public UserResponse update(@RequestBody UserRequest userRequest) {
        return userService.update(userRequest);
    }

    @DeleteMapping("/admin/users")
    public void delete(@RequestBody UserRequest userRequest){
        userService.delete(userRequest);
    }
}
