package com.opticalarc.registration_form.service;

import java.time.LocalDateTime;

public interface OtpService {

    String generateOtp();

    LocalDateTime getOtpExpiry();

    void sendOtpEmail(String email, String otp);

}
