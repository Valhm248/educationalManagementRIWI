package com.riwi.educationalManagement.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompleteLessonInformationResponse {
    private Long id;
    private String lessonTitle;
    private String content;
    private CourseToUserResponse course;  
}
