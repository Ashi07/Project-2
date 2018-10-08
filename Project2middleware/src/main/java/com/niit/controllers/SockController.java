package com.niit.controllers;





import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.apache.commons.logging.Log;

import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import org.springframework.messaging.simp.annotation.SendToUser;



import org.springframework.messaging.simp.annotation.SubscribeMapping;

import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.stereotype.Controller;



import com.niit.Dao.UserDao;
import com.niit.model.Chat;
import com.niit.model.ChatOutput;


@Controller
public class SockController{
  
	@MessageMapping("/chat")
    @SendTo("/topic/message")
    public ChatOutput  sendMessage(Chat chat)
    {
    	System.out.println("Message Recieved");
        return new ChatOutput(chat,new Date());
    }
}