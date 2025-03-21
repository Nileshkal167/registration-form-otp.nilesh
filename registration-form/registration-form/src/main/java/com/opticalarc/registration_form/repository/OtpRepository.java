package com.opticalarc.registration_form.repository;

import com.opticalarc.registration_form.entity.OTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<OTP, Long> {

    Optional<OTP> findByOtp(String otp);

}
