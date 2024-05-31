package com.riwi.educationalManagement.infraestructure.service;

import com.riwi.educationalManagement.api.dto.request.LessonRequest;
import com.riwi.educationalManagement.api.dto.response.CompleteLessonInformationResponse;
import com.riwi.educationalManagement.api.dto.response.CourseToUserResponse;
import com.riwi.educationalManagement.api.dto.response.UserInfoResponse;
import com.riwi.educationalManagement.domain.entities.Course;
import com.riwi.educationalManagement.domain.entities.Lesson;
import com.riwi.educationalManagement.domain.repositories.CourseRepository;
import com.riwi.educationalManagement.domain.repositories.LessonRepository;
import com.riwi.educationalManagement.infraestructure.abstract_service.ILessonService;
import com.riwi.educationalManagement.utils.exception.BadRequestException;
import com.riwi.educationalManagement.utils.message.ErrorMessages;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class LessonService implements ILessonService{

    @Autowired
    private final LessonRepository lessonRepository;

    @Autowired
    private final CourseRepository courseRepository;


    @Override
    public CompleteLessonInformationResponse create(LessonRequest request) {

        Course course = this.courseRepository.findById(request.getCourseId())
        .orElseThrow(()-> new BadRequestException("Course"));

        Lesson lesson = this.requestToEntity(request);
        lesson.setCourse(course);                
        
        return this.entityToResponse(this.lessonRepository.save(lesson));
    }

    @Override
    public CompleteLessonInformationResponse get(Long id) {
        return this.entityToResponse(this.find(id));
    }

    @Override
    public Page<CompleteLessonInformationResponse> getAll(int page, int size) {
        if (page < 0)
            page = 0;

        PageRequest pagination = PageRequest.of(page, size);

        return this.lessonRepository.findAll(pagination)
            .map(this::entityToResponse);
    }

    @Override
    public CompleteLessonInformationResponse update(LessonRequest request, Long id) {
        Lesson lesson = this.find(id);
        Lesson lessonUpdate = this.requestToEntity(request);
        lessonUpdate.setId(id);

        return this.entityToResponse(this.lessonRepository.save(lessonUpdate));
    }

    @Override
    public void delete(Long id) {
        this.lessonRepository.delete(this.find(id));
    }

    private CompleteLessonInformationResponse entityToResponse(Lesson entity){

        return CompleteLessonInformationResponse.builder()
                .id(entity.getId())
                .lessonTitle(entity.getLessonTitle())
                .content(entity.getContent())
                .course(CourseToUserResponse.builder()
                        .id(entity.getCourse().getId())
                        .courseName(entity.getCourse().getCourseName())
                        .description(entity.getCourse().getDescription())
                        .userInfoResponse(UserInfoResponse.builder()
                                .id(entity.getCourse().getUser().getId())
                                .username(entity.getCourse().getUser().getUsername())
                                .email(entity.getCourse().getUser().getEmail())
                                .fullName(entity.getCourse().getUser().getFullName())
                                .role(entity.getCourse().getUser().getRole())
                                .build())
                        .build())
                .build();
    }

    private Lesson requestToEntity(LessonRequest request){

        return Lesson.builder()
                .lessonTitle(request.getLessonTitle())
                .content(request.getContent())
                .course(Course.builder()
                        .id(request.getCourseId())
                        .build())
                .build();
    }

    private Lesson find(Long id){
        return this.lessonRepository.findById(id)
            .orElseThrow(()-> new BadRequestException(ErrorMessages.IdNotFound("Lesson")));
    }
}
