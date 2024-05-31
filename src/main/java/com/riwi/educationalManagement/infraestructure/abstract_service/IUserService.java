package com.riwi.educationalManagement.infraestructure.abstract_service;

import com.riwi.educationalManagement.api.dto.request.UserRequest;
import com.riwi.educationalManagement.api.dto.response.UserResponse;

public interface IUserService extends CrudServices<UserRequest, UserResponse, Long>{ 
}
