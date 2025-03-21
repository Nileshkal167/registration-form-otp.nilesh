package com.opticalarc.registration_form.serviceImpl;

import com.opticalarc.registration_form.DTO.UserRequestDTO;
import com.opticalarc.registration_form.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
public class OtpServiceImpl implements OtpService {

    private final SecureRandom random = new SecureRandom();
    private final JavaMailSender javaMailSender;


    @Value("${spring.mail.properties.from}")
    private String from;


    public OtpServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    @Override
    public String generateOtp() {
        int otp = 100000 + random.nextInt(900000); // 6-digit OTP
        UserRequestDTO.builder()
                .otpExpiry(LocalDateTime.now());
        return String.valueOf(otp);
    }


    @Override
    public LocalDateTime getOtpExpiry() {
        return LocalDateTime.now().plusMinutes(5); // OTP valid for 5 minutes
    }


    @Override
    public void sendOtpEmail(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your OTP For Verification");
        message.setText("Your OTP is : " + otp);
        message.setFrom(from);
        javaMailSender.send(message);
        System.out.println(message);
    }


}
