package com.opticalarc.registration_form.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Long mobileNumber;

    private String Address;

    private String area;

}
