package com.riwi.educationalManagement.infraestructure.service;

import com.riwi.educationalManagement.api.dto.request.CourseRequest;
import com.riwi.educationalManagement.api.dto.response.CourseToUserResponse;
import com.riwi.educationalManagement.api.dto.response.UserInfoResponse;
import com.riwi.educationalManagement.domain.entities.Course;
import com.riwi.educationalManagement.domain.entities.User;
import com.riwi.educationalManagement.domain.repositories.CourseRepository;
import com.riwi.educationalManagement.domain.repositories.UserRepository;
import com.riwi.educationalManagement.infraestructure.abstract_service.ICourseService;
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
public class CourseService implements ICourseService{
   
    @Autowired
    private final CourseRepository courseRepository;

    @Autowired
    private final UserRepository userRepository;

    @Override
    public CourseToUserResponse create(CourseRequest request) {

        User user = this.userRepository.findById(request.getInstructorId())
        .orElseThrow(()-> new BadRequestException("User"));

        Course course = this.requestToEntity(request);

        course.setUser(user);                
        
        return this.entityToResponse(this.courseRepository.save(course));
    }

    @Override
    public CourseToUserResponse get(Long id) {
        return this.entityToResponse(this.find(id));
    }

    @Override
    public Page<CourseToUserResponse> getAll(int page, int size) {
        if (page < 0)
            page = 0;

        PageRequest pagination = PageRequest.of(page, size);

        return this.courseRepository.findAll(pagination)
            .map(this::entityToResponse);
    }

    @Override
    public CourseToUserResponse update(CourseRequest request, Long id) {
        Course course = this.find(id);
        Course courseUpdate = this.requestToEntity(request);
        courseUpdate.setId(id);

        return this.entityToResponse(this.courseRepository.save(courseUpdate));
    }

    @Override
    public void delete(Long id) {
        this.courseRepository.delete(this.find(id));
    }

    private CourseToUserResponse entityToResponse(Course entity) {

        return CourseToUserResponse.builder()
                .id(entity.getId())
                .courseName(entity.getCourseName())
                .description(entity.getDescription())
                .userInfoResponse(UserInfoResponse.builder()
                        .id(entity.getUser().getId())
                        .username(entity.getUser().getUsername())
                        .email(entity.getUser().getEmail())
                        .fullName(entity.getUser().getFullName())
                        .role(entity.getUser().getRole())
                        .build())
                .build();
    }

    private Course requestToEntity(CourseRequest request) {
        return Course.builder()
                .courseName(request.getCourseName())
                .description(request.getDescription())
                .user(User.builder()
                        .id(request.getInstructorId())
                        .build())
                .build();
    }

    private Course find(Long id) {
        return this.courseRepository.findById(id).orElseThrow(() -> new BadRequestException((ErrorMessages.IdNotFound("Course"))));
    }
}
