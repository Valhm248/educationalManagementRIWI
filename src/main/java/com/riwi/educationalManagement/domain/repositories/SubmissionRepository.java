package com.riwi.educationalManagement.domain.repositories;

import com.riwi.educationalManagement.domain.entities.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long>{
}
