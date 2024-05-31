package com.riwi.educationalManagement.api.controller;

import com.riwi.educationalManagement.api.dto.request.LessonRequest;
import com.riwi.educationalManagement.api.dto.response.CompleteLessonInformationResponse;
import com.riwi.educationalManagement.infraestructure.abstract_service.ILessonService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/lessons")
@AllArgsConstructor
public class LessonsController {
    private final ILessonService lessonService;

    @GetMapping
    public ResponseEntity<Page<CompleteLessonInformationResponse>> getAll(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size){
        return  ResponseEntity.ok(this.lessonService.getAll(page -1, size));
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<CompleteLessonInformationResponse> get(@PathVariable Long id){
        return ResponseEntity.ok(this.lessonService.get(id));
    }

    @PostMapping
    public ResponseEntity<CompleteLessonInformationResponse> create(@Validated @RequestBody LessonRequest request){
        return ResponseEntity.ok(this.lessonService.create(request));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<CompleteLessonInformationResponse> update(@Validated @RequestBody LessonRequest request, @PathVariable Long id){
        return ResponseEntity.ok(this.lessonService.update(request, id));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.lessonService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
