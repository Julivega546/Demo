package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @GetMapping("/")
    public String home() {
        return "Backend OK – Servidor corriendo en AWS";
    }

    @GetMapping("/status")
    public String status() {
        return "API funcionando correctamente";
    }
}
