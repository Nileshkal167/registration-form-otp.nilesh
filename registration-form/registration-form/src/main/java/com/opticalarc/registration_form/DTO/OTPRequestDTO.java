package com.opticalarc.registration_form.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OTPRequestDTO {

    private String email;

    private String otp;

    private LocalDateTime otpExpiry;
}
