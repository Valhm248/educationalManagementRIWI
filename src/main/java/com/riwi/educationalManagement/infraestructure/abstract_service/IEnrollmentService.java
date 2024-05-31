package com.riwi.educationalManagement.infraestructure.abstract_service;

import com.riwi.educationalManagement.api.dto.request.EnrollmentRequest;
import com.riwi.educationalManagement.api.dto.response.EnrollmentResponse;

public interface IEnrollmentService extends CrudServices<EnrollmentRequest, EnrollmentResponse, Long>{
}
