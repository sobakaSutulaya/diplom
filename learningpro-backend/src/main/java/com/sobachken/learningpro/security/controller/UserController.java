package com.sobachken.learningpro.security.controller;

import com.sobachken.learningpro.security.service.UserDetailsServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.security.Principal;

import static com.sobachken.learningpro.common.ApiParam.LOGIN;
import static com.sobachken.learningpro.common.ApiPath.*;

@RestController
@RequestMapping(USERS_PATH)
public class UserController {

    private final UserDetailsServiceImpl userDetailsService;

    public UserController(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping(ID_PATH)
    public ResponseEntity<?> getUserId(@PathParam(LOGIN) String login) {
        return ResponseEntity.ok(this.userDetailsService.getLoggedUserId(login));
    }

    @GetMapping(ME_PATH)
    public Principal user(Principal principal) {
        return principal;
    }
}
