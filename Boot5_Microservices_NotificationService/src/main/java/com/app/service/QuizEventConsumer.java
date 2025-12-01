package com.app.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service 
public class QuizEventConsumer { 
 

@KafkaListener(topics = "quiz-events", groupId = "notification-group") 
    public void listen(String message) { 
        System.out.println("Sending notification: " + message); 
        // later we  can send email or SMS here 
    } 
} 
 