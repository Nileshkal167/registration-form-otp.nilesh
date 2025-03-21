package com.opticalarc.registration_form.service;

import com.opticalarc.registration_form.DTO.UserRequestDTO;

public interface UserService {

    String registerUser(UserRequestDTO userRequestDTO, String otp);
}
