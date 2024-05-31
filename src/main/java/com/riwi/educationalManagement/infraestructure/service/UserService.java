package com.riwi.educationalManagement.infraestructure.service;

import com.riwi.educationalManagement.api.dto.request.UserRequest;
import com.riwi.educationalManagement.api.dto.response.UserResponse;
import com.riwi.educationalManagement.domain.entities.User;
import com.riwi.educationalManagement.domain.repositories.UserRepository;
import com.riwi.educationalManagement.infraestructure.abstract_service.IUserService;
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
public class UserService implements IUserService{

    @Autowired
    private final UserRepository userRepository;

    @Override
    public UserResponse create(UserRequest request) {
        User user = this.requestToEntity(request);
        return this.entityToResponse(this.userRepository.save(user));
    }

    @Override
    public UserResponse get(Long id) {
        return this.entityToResponse(this.find(id));
    }

    @Override
    public Page<UserResponse> getAll(int page, int size) {
        if (page < 0) page = 0;
        PageRequest pagination = PageRequest.of(page, size);

        return this.userRepository.findAll(pagination).map(this::entityToResponse);
    }

    @Override
    public UserResponse update(UserRequest request, Long id) {
        User user = this.find(id);
        User userUpdate = this.requestToEntity(request);
        userUpdate.setId(id);

        return this.entityToResponse(this.userRepository.save(userUpdate));
    }

    @Override
    public void delete(Long id) {
       this.userRepository.delete(this.find(id));
    }

    private UserResponse entityToResponse(User entity){
        return UserResponse.builder()
           .id(entity.getId())
           .username(entity.getUsername())
           .email(entity.getEmail())
           .fullName(entity.getFullName())
           .password(entity.getPassword())
           .role(entity.getRole())
           .build();
    }

    private User requestToEntity(UserRequest request){
        return User.builder()
           .username(request.getUsername())
           .email(request.getEmail())
           .fullName(request.getFullName())
           .password(request.getPassword())
           .role(request.getRole())
           .build();
    }

    private User find(Long id){
        return this.userRepository.findById(id)
            .orElseThrow(()-> new BadRequestException(ErrorMessages.IdNotFound("User")));
    }
}
