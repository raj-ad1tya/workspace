package com.examly.springappuser.controller;

import com.examly.springappuser.model.LoginDTO;
import com.examly.springappuser.model.User;
import com.examly.springappuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<User>(userService.createUser(user), HttpStatusCode.valueOf(201));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDTO> loginUser(@RequestBody User user) {
        LoginDTO loginDTO = userService.loginUser(user);
        int status = 200;

        if(loginDTO == null)
            status = 401;

        return new ResponseEntity<LoginDTO>(loginDTO, HttpStatusCode.valueOf(status));
    }

}
