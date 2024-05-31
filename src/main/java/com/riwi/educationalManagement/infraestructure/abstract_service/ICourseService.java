package com.riwi.educationalManagement.infraestructure.abstract_service;

import com.riwi.educationalManagement.api.dto.request.CourseRequest;
import com.riwi.educationalManagement.api.dto.response.CourseToUserResponse;

public interface ICourseService extends CrudServices<CourseRequest, CourseToUserResponse, Long>{
}
