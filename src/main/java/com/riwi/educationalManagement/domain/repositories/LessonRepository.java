package com.riwi.educationalManagement.domain.repositories;

import com.riwi.educationalManagement.domain.entities.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long>{
}
