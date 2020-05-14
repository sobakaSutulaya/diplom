package com.sobachken.learningpro.security.controller;

import com.sobachken.learningpro.security.service.UserDetailsServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.security.Principal;

@RestController
@RequestMapping("learning-pro/users/")
public class UserController {

    private final UserDetailsServiceImpl userDetailsService;

    public UserController(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/id")
    public ResponseEntity<?> getUserId(@PathParam("login") String login) {
        return ResponseEntity.ok(this.userDetailsService.getLoggedUserId(login));
    }

    @GetMapping("/me")
    public Principal user(Principal principal) {
        return principal;
    }
}
