package com.riwi.educationalManagement.infraestructure.service;

import com.riwi.educationalManagement.api.dto.request.SubmissionRequest;
import com.riwi.educationalManagement.api.dto.response.SubmissionResponse;
import com.riwi.educationalManagement.infraestructure.abstract_service.ISubmissionService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class SubmissionService implements ISubmissionService{
    @Override
    public SubmissionResponse create(SubmissionRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public SubmissionResponse get(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public Page<SubmissionResponse> getAll(int page, int size) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public SubmissionResponse update(SubmissionRequest request, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
}
