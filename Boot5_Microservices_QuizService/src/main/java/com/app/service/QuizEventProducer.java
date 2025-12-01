package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service 
public class QuizEventProducer { 
 

@Autowired 
    private KafkaTemplate<String, String> kafkaTemplate; 
 

public void sendQuizCompletedEvent(String message) { 
        kafkaTemplate.send("quiz-events", message); 
    } 
}