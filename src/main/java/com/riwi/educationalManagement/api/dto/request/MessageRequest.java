package com.riwi.educationalManagement.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequest {

    private String messageContent;

    private LocalDateTime sentDate;

    private Long senderId;

    private String userWhoSentMessage;

    private Long receiverId;

    private Long courseId;
}
