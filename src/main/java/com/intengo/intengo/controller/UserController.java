package com.intengo.intengo.controller;

import com.intengo.intengo.domain.User;
import com.intengo.intengo.service.UserService;
import com.intengo.intengo.vm.UserVm;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public User saveUser(@RequestBody UserVm user) {

        User saveUser = new User();
        saveUser.setUsername(user.getUserName());
        saveUser.setPassword(user.getPassword());
        return userService.save(saveUser);
    }
}
