package com.riwi.educationalManagement.domain.repositories;

import com.riwi.educationalManagement.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
}
