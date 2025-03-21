package com.opticalarc.registration_form.DTO;

import lombok.Data;

@Data
public class OtpVerifyRequestDTO {

    private String email;
    private String otp;
}
