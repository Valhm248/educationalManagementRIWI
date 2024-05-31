package com.riwi.educationalManagement.infraestructure.abstract_service;

import com.riwi.educationalManagement.api.dto.request.LessonRequest;
import com.riwi.educationalManagement.api.dto.response.CompleteLessonInformationResponse;

public interface ILessonService extends CrudServices<LessonRequest, CompleteLessonInformationResponse, Long>{
}
