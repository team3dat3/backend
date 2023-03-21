package com.team3dat3.backend.api.v1;

import com.team3dat3.backend.dto.user.UserRequest;
import com.team3dat3.backend.dto.user.UserResponse;
import com.team3dat3.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/users")
@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponse> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserResponse find(@PathVariable("id") int id) {
        return userService.find(id);
    }

    @PostMapping
    public UserResponse create(@RequestBody UserRequest userRequest){
        return userService.create(userRequest);
    }

    @PatchMapping
    public UserResponse update(@RequestBody UserRequest userRequest) {
        return userService.update(userRequest);
    }

    @DeleteMapping
    public void delete(@RequestBody UserRequest userRequest){
        userService.delete(userRequest);
    }
}
