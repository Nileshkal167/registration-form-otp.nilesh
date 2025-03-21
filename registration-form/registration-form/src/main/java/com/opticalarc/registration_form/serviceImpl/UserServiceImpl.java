package com.opticalarc.registration_form.serviceImpl;

import com.opticalarc.registration_form.DTO.UserRequestDTO;
import com.opticalarc.registration_form.DTO.UserResponseDTO;
import com.opticalarc.registration_form.entity.OTP;
import com.opticalarc.registration_form.entity.User;
import com.opticalarc.registration_form.exception.CustomException;
import com.opticalarc.registration_form.repository.OtpRepository;
import com.opticalarc.registration_form.repository.UserRepository;
import com.opticalarc.registration_form.service.OtpService;
import com.opticalarc.registration_form.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final OtpService otpService;

    private final OtpRepository otpRepository;

    public UserServiceImpl(UserRepository userRepository, @Lazy OtpService otpService, OtpRepository otpRepository) {
        this.userRepository = userRepository;
        this.otpService = otpService;
        this.otpRepository = otpRepository;
    }

    @Override
    public String registerUser(UserRequestDTO userRequestDTO,String otp) {


        if (otp != null) {
            OTP existedOtp = otpRepository.findByOtp(otp)
                    .orElseThrow(() -> new CustomException("OTP Not Found...!"));

            if (userRequestDTO != null) {
                User user = User.builder()
                        .Address(userRequestDTO.getAddress())
                        .email(userRequestDTO.getEmail())
                        .area(userRequestDTO.getArea())
                        .firstName(userRequestDTO.getFirstName())
                        .lastName(userRequestDTO.getLastName())
                        .mobileNumber(userRequestDTO.getMobileNumber())
                        .password(userRequestDTO.getPassword())
                        .build();

                if (existedOtp.getOtp().equals(otp) && existedOtp.getOtpExpiry().isAfter(LocalDateTime.now()) && userRequestDTO.getEmail().equals(existedOtp.getEmail())) {
                    userRepository.save(user);
                    otpRepository.delete(existedOtp);
                    return "User Saved Successfully...!";
                } else {
                    return "Invalid OTP...!";
                }
            }

            } else if (userRequestDTO != null) {
                String generateOtp = otpService.generateOtp();
                LocalDateTime otpExpiry = LocalDateTime.now();
                OTP otp1 = OTP.builder()
                        .otp(generateOtp)
                        .email(userRequestDTO.getEmail())
                        .otpExpiry(LocalDateTime.now().plusMinutes(5))
                        .build();
                otpRepository.save(otp1);
                otpService.sendOtpEmail(userRequestDTO.getEmail(), generateOtp);
                return "OTP Sent Successfully...!";
            }
        return "Invalid Request....!";
    }

    public static UserResponseDTO userToUserResponseDTO(User user) {
       return UserResponseDTO.builder()
                .id(user.getId())
                .Address(user.getAddress())
                .email(user.getEmail())
                .area(user.getArea())
                .firstName(user.getFirstName())
                .mobileNumber(user.getMobileNumber())
                .lastName(user.getLastName())
                .build();
    }

    public static User userResponseDTOToUser(UserResponseDTO userResponseDTO) {
        return User.builder()
                .id(userResponseDTO.getId())
                .Address(userResponseDTO.getAddress())
                .email(userResponseDTO.getEmail())
                .area(userResponseDTO.getArea())
                .firstName(userResponseDTO.getFirstName())
                .mobileNumber(userResponseDTO.getMobileNumber())
                .lastName(userResponseDTO.getLastName())
                .build();
    }

}
