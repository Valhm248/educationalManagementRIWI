package com.riwi.educationalManagement.infraestructure.abstract_service;

import com.riwi.educationalManagement.api.dto.request.MessageRequest;
import com.riwi.educationalManagement.api.dto.response.MessageResponse;

public interface IMessageService extends CrudServices<MessageRequest, MessageResponse, Long>{
}
