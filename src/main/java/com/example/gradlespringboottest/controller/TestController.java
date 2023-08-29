package com.example.gradlespringboottest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "<html><body><br>Hello!!</br><p><h3>jenkins_gradle_build_example!!!</h3></body></html>";
    }
}
