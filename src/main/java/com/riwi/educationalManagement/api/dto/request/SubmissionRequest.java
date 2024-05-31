package com.riwi.educationalManagement.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionRequest {
    private String content;
    private LocalDateTime submissionDate;
    private double grade;
    private Long assignmentId;
    private Long userId;
}
