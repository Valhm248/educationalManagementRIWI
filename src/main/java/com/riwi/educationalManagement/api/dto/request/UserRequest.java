package com.riwi.educationalManagement.api.dto.request;

import com.riwi.educationalManagement.utils.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NotBlank(message = "Username is required")
    @Size(min = 1, max = 50, message = "The username must have a maximum of 50 characters.")
    private String username;
    @NotBlank(message = "Password is required")
    private String password;
    @NotBlank(message = "Email is required")
    @Size(min = 1, max = 100, message = "The email must have a maximum of 100 characters.")
    private String email;
    @Size(min = 1, max = 100, message = "The full name must have a maximum of 100 characters.")
    private String fullName;
    private Role role;
    private Long enrollmentId;
    private Long courseId;
    private Long messageSenderId;
    private Long messageReceiverId;
    private Long submissionId;
}
