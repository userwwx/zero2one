package com.wwx.zero2one.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitController {

    @GetMapping("/hello")
    public String hello(){
        return "hello world";
    }
}
