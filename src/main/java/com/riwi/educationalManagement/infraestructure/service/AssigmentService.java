package com.riwi.educationalManagement.infraestructure.service;

import com.riwi.educationalManagement.api.dto.request.AssignmentRequest;
import com.riwi.educationalManagement.api.dto.response.CompleteAssignmentInfo;
import com.riwi.educationalManagement.api.dto.response.CompleteLessonInformationResponse;
import com.riwi.educationalManagement.api.dto.response.CourseToUserResponse;
import com.riwi.educationalManagement.api.dto.response.UserInfoResponse;
import com.riwi.educationalManagement.domain.entities.Assignment;
import com.riwi.educationalManagement.domain.entities.Lesson;
import com.riwi.educationalManagement.domain.repositories.AssignmentRepository;
import com.riwi.educationalManagement.domain.repositories.LessonRepository;
import com.riwi.educationalManagement.infraestructure.abstract_service.IAssignmentService;
import com.riwi.educationalManagement.utils.exception.BadRequestException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class AssigmentService implements IAssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final LessonRepository lessonRepository;


    @Override
    public CompleteAssignmentInfo create(AssignmentRequest request) {

        Lesson lesson = this.lessonRepository.findById(request.getLessonId())
                .orElseThrow(()-> new BadRequestException("Lesson"));

        Assignment assignment = this.requestToEntity(request);
        assignment.setLesson(lesson);

        return this.entityToResponse(this.assignmentRepository.save(assignment));
    }

    @Override
    public CompleteAssignmentInfo get(Long id) {
        return this.entityToResponse(this.find(id));
    }

    @Override
    public Page<CompleteAssignmentInfo> getAll(int page, int size) {
        if (page < 0)
            page = 0;

        PageRequest pagination = PageRequest.of(page, size);

        return this.assignmentRepository.findAll(pagination)
                .map(this::entityToResponse);
    }

    @Override
    public CompleteAssignmentInfo update(AssignmentRequest request, Long id) {

        Assignment assignment = this.find(id);

        Assignment assignmentUpdate = this.requestToEntity(request);

        assignmentUpdate.setId(id);

        return this.entityToResponse(this.assignmentRepository.save(assignmentUpdate));
    }

    @Override
    public void delete(Long id) {
        this.assignmentRepository.delete(this.find(id));
    }

    private Assignment find(Long id) {
        return this.assignmentRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Assignment"));
    }

    private Assignment requestToEntity(AssignmentRequest request) {

        return Assignment.builder()
                .assignmentTitle(request.getAssignmentTitle())
                .description(request.getDescription())
                .dueDate(request.getDueDate())
                .lesson(Lesson.builder()
                        .id(request.getLessonId())
                        .build())
                .build();
    }

    private CompleteAssignmentInfo entityToResponse(Assignment assignment) {

        return CompleteAssignmentInfo.builder()
                .id(assignment.getId())
                .assignmentTitle(assignment.getAssignmentTitle())
                .description(assignment.getDescription())
                .dueDate(assignment.getDueDate())
                .lessonInfo(CompleteLessonInformationResponse.builder()
                        .id(assignment.getLesson().getId())
                        .lessonTitle(assignment.getLesson().getLessonTitle())
                        .content(assignment.getLesson().getContent())
                        .course(CourseToUserResponse.builder()
                                .id(assignment.getLesson().getCourse().getId())
                                .courseName(assignment.getLesson().getCourse().getCourseName())
                                .description(assignment.getLesson().getCourse().getDescription())
                                .userInfoResponse(UserInfoResponse.builder()
                                        .id(assignment.getLesson().getCourse().getUser().getId())
                                        .username(assignment.getLesson().getCourse().getUser().getUsername())
                                        .email(assignment.getLesson().getCourse().getUser().getEmail())
                                        .fullName(assignment.getLesson().getCourse().getUser().getFullName())
                                        .role(assignment.getLesson().getCourse().getUser().getRole())
                                        .build())
                                .build())
                        .build())
                .build();
    }
}
