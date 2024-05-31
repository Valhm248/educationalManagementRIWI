package com.riwi.educationalManagement.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentRequest {
    @NotBlank(message = "The title is required")

    private String assignmentTitle;

    private String description;

    private LocalDate dueDate;

    private Long lessonId;
}
