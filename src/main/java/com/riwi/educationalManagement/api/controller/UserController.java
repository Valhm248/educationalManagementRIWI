package com.riwi.educationalManagement.api.controller;

import com.riwi.educationalManagement.api.dto.request.UserRequest;
import com.riwi.educationalManagement.api.dto.response.UserResponse;
import com.riwi.educationalManagement.infraestructure.abstract_service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
@AllArgsConstructor
public class UserController {
    private final IUserService userService;

    @GetMapping
    public ResponseEntity<Page<UserResponse>> getAll(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size){
        return  ResponseEntity.ok(this.userService.getAll(page -1, size));
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<UserResponse> get(@PathVariable Long id){
        return ResponseEntity.ok(this.userService.get(id));
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@Validated @RequestBody UserRequest request){
        return ResponseEntity.ok(this.userService.create(request));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<UserResponse> update(@Validated @RequestBody UserRequest request, @PathVariable Long id){
        return ResponseEntity.ok(this.userService.update(request, id));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
