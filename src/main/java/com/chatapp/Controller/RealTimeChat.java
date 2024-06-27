package com.chatapp.Controller;

import com.chatapp.Model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class RealTimeChat {

    private static final Logger logger = LoggerFactory.getLogger(RealTimeChat.class);

    private final SimpMessagingTemplate simpMessagingTemplate;

    public RealTimeChat(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/message")
    @SendTo("/group/public")
    public Message receiveMessage(@Payload Message message) {
        message.setTimestamp(LocalDateTime.now());
        logger.info("Received message: {}", message.getContent());
        simpMessagingTemplate.convertAndSend("/group/" + message.getChat().getId(), message);
        logger.info("Broadcasted message to /group/{}", message.getChat().getId());
        return message;
    }
}