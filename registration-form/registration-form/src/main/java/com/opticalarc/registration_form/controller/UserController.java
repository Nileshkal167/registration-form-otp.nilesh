package com.opticalarc.registration_form.controller;

import com.opticalarc.registration_form.DTO.UserRequestDTO;
import com.opticalarc.registration_form.exception.CustomException;
import com.opticalarc.registration_form.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping({"/register", "/register/{otp}"})
    public ResponseEntity<String> registration(@RequestBody UserRequestDTO userRequestDTO
            ,@PathVariable(required = false) String otp) {
        if (userRequestDTO != null) {
            String savedUser = userService.registerUser(userRequestDTO, otp);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        }
        throw new CustomException("Invalid user Data");
    }

}
