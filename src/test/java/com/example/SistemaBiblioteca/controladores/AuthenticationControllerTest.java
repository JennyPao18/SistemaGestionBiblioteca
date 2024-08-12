package com.example.SistemaBiblioteca.controladores;

import com.example.SistemaBiblioteca.dtos.AuthenticationDto;
import com.example.SistemaBiblioteca.dtos.LoginDto;
import com.example.SistemaBiblioteca.dtos.RegisterDto;
import com.example.SistemaBiblioteca.servicios.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AuthenticationControllerTest {
    @Mock
    private AuthenticationService authService;

    @InjectMocks
    private AuthenticationController authController;

    public AuthenticationControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void InicioDeSesionExitoso() {
        LoginDto loginDto = new LoginDto("Paola", "123");
        AuthenticationDto authDto = new AuthenticationDto("token");

        when(authService.login(loginDto)).thenReturn(authDto);

        ResponseEntity<AuthenticationDto> response = authController.login(loginDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(authDto, response.getBody());
    }

    @Test
    public void RegistroExitoso() {
        RegisterDto registerDto = new RegisterDto("Paola", 18, 18, "jp@email.com", "12345");
        AuthenticationDto authDto = new AuthenticationDto("token");

        when(authService.register(registerDto)).thenReturn(authDto);

        ResponseEntity<AuthenticationDto> response = authController.register(registerDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(authDto, response.getBody());
    }

    @Test
    public void InicioDeSesionVacio() {
        LoginDto loginDto = new LoginDto("Paola", "123");
        when(authService.login(loginDto)).thenReturn(null);
        ResponseEntity<AuthenticationDto> response = authController.login(loginDto);
        System.out.println("Debe completar los datos solicitados");
    }

    @Test
    public void RegistroVacio() {
        RegisterDto registerDto = new RegisterDto("Paola", 18, 18, "jp@email.com", "12345");
        when(authService.register(registerDto)).thenReturn(null);
        ResponseEntity<AuthenticationDto> response = authController.register(registerDto);
        System.out.println("Debe completar los datos solicitados");
    }

}
