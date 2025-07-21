// src/main/java/com/bancodealimentos/controller/WebController.java
package com.bancodealimentos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    
    @GetMapping("/")
    public String index() {
        return "forward:/index.html";
    }
}