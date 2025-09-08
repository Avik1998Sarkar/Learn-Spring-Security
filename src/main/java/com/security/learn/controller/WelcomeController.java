package com.security.learn.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("")
    public String welcome() {
        return "Welcome to the Spring Security Application!";
    }

    @GetMapping("/csrf")
    public CsrfToken getCsrfToken(HttpServletRequest csrfRequest) {
        CsrfToken token = (CsrfToken) csrfRequest.getAttribute("_csrf");
        return token;
    }
}
