package com.example.langoal.web;

import com.example.langoal.entities.ChatMessages;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Objects;

@Controller
public class ChatController {
    @MessageMapping("/chat.register")
    @SendTo("/topic/public")
    public ChatMessages register(@Payload ChatMessages chatMessages, SimpMessageHeaderAccessor headerAccessor) {
        Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("username", chatMessages.getSender());
        return chatMessages;
    }
    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatMessages SendMessages(@Payload ChatMessages chatMessages) {
        return chatMessages;
    }
}
