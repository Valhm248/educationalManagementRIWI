package com.riwi.educationalManagement.domain.repositories;

import com.riwi.educationalManagement.domain.entities.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long>{
}
