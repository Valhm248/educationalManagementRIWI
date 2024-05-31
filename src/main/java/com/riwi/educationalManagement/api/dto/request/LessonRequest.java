package com.riwi.educationalManagement.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonRequest {
    @NotBlank(message = "The title is required")

    private String lessonTitle;

    private String content;

    private Long courseId;
}
