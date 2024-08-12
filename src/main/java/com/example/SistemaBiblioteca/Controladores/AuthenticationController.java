package com.example.SistemaBiblioteca.Controladores;

import com.example.SistemaBiblioteca.Dtos.AuthenticationDto;
import com.example.SistemaBiblioteca.Dtos.LoginDto;
import com.example.SistemaBiblioteca.Dtos.RegisterDto;
import com.example.SistemaBiblioteca.Servicios.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authService;


    @PostMapping(value = "/login")
    public ResponseEntity<AuthenticationDto> login(@RequestBody LoginDto loginDto) {
        AuthenticationDto authDto = authService.login(loginDto);
        return ResponseEntity.ok(authDto);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationDto> register(@RequestBody RegisterDto registerDto) {
        AuthenticationDto authDto = authService.register(registerDto);
        return ResponseEntity.ok(authDto);
    }
}
