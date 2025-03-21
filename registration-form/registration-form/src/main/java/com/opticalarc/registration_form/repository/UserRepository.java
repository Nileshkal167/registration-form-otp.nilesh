package com.opticalarc.registration_form.repository;

import com.opticalarc.registration_form.DTO.UserRequestDTO;
import com.opticalarc.registration_form.DTO.UserResponseDTO;
import com.opticalarc.registration_form.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
