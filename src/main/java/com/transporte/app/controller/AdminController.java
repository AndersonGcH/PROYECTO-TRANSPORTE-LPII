package com.transporte.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/administrador/index")
public String adminIndex() {
    return "/administrador/index"; 
}
}