package com.api.testeKhipo.controller;

import com.api.testeKhipo.dto.AuthenticationResponse;
import com.api.testeKhipo.dto.LoginDTO;

import com.api.testeKhipo.dto.ResgistroDTO;
import com.api.testeKhipo.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/v1/login") @RequiredArgsConstructor
public class LoginController {

    private final AuthenticationService authenticationService;

    @PostMapping(path = "/registrar")
    public ResponseEntity<AuthenticationResponse> registro(
            @RequestBody ResgistroDTO registro) {
            return new ResponseEntity<>(authenticationService.registro(registro), HttpStatus.CREATED);
    }

    @PostMapping(path = "")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody LoginDTO login){
        return ResponseEntity.ok(authenticationService.login(login));
    }
}
