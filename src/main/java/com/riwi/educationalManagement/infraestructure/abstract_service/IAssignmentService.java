package com.riwi.educationalManagement.infraestructure.abstract_service;

import com.riwi.educationalManagement.api.dto.request.AssignmentRequest;
import com.riwi.educationalManagement.api.dto.response.CompleteAssignmentInfo;

public interface IAssignmentService extends CrudServices<AssignmentRequest, CompleteAssignmentInfo, Long>{
}
