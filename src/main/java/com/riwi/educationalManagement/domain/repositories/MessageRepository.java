package com.riwi.educationalManagement.domain.repositories;

import com.riwi.educationalManagement.domain.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{
}
