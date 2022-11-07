package com.microservice.eurekaclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class ClientController {

    @Value("${eureka.instance.instance-id}")
    private String name;

    @GetMapping("/test")
    public String test(){
        return name;
    }
}
