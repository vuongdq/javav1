package com.vdq.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "web")
public class WebController {
    @GetMapping("hello")
    public String Hello(){
        return "web/hello";
    }
}
