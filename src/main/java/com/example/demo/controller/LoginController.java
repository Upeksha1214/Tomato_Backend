package com.example.demo.controller;

import com.example.demo.dto.LoginDTO;
import com.example.demo.service.LoginService;
import com.example.demo.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping(path = "user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response login(@RequestBody LoginDTO dto) {
        if (loginService.login(dto)) {
            return new Response(200, "Login success");
        } else {
            return new Response(400, "Error!");
        }
    }
}
