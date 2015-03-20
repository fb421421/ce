package com.gaming.ce.user.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gaming.ce.common.constant.Role;

@Controller
public class GreetingController {


	@RolesAllowed(Role.USER)
    @MessageMapping("/hello")
	@RequestMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
    	System.out.println("message:"+message);
        Thread.sleep(3000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }

}
