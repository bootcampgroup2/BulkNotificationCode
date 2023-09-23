package com.casestudy.emailservice.controller;

import com.casestudy.emailservice.kafka.MessageService;
import com.casestudy.emailservice.models.Ordermail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;


@RestController
@RequestMapping("/api/message")
public class MessageController {
    @Autowired
    private MessageService messageService;
    public static final Logger LOGGER=LoggerFactory.getLogger(MessageController.class);
    @GetMapping("/{email}")
    public List<Ordermail> getUnreadMessages(@PathVariable String email) {
    	LOGGER.info("Order place......notification is been sent to user"+email);
        return messageService.getUnreadMessagesByEmail(email);
    }
}
