package com.riwi.educationalManagement.infraestructure.abstract_service;

import com.riwi.educationalManagement.api.dto.request.SubmissionRequest;
import com.riwi.educationalManagement.api.dto.response.SubmissionResponse;

public interface ISubmissionService extends CrudServices<SubmissionRequest, SubmissionResponse, Long>{
}
