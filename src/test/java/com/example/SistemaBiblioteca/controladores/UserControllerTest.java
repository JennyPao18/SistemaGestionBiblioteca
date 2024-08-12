package com.example.SistemaBiblioteca.controladores;

import com.example.SistemaBiblioteca.dtos.UserDto;
import com.example.SistemaBiblioteca.extra.Role;
import com.example.SistemaBiblioteca.servicios.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void mostrarListaDeUsuarios() {
        UserDto user1 = new UserDto("1", "Paola", 18, 123464678, "paolita@example.com", "aeiou", Role.USER);
        UserDto user2 = new UserDto("2", "Jenny", 17, 123464678, "jenny@example.com", "aeiou", Role.USER);
        List<UserDto> users = Arrays.asList(user1, user2);

        when(userService.mostrarTodo()).thenReturn(users);

        ResponseEntity<List<UserDto>> response = userController.mostrarTodo();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertEquals("Paola", response.getBody().get(0).getNombre());
        assertEquals("Jenny", response.getBody().get(1).getNombre());
    }

    @Test
    public void buscarUsuarioPorId() {
        UserDto user = new UserDto("1", "Paola", 18, 123464678, "paolita@example.com", "aeiou", Role.USER);

        when(userService.buscarPorId("1")).thenReturn(user);

        ResponseEntity<UserDto> response = userController.buscarPorId("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Paola", response.getBody().getNombre());
    }

    @Test
    public void crearUsuario() {
        UserDto user = new UserDto("1", "Paola", 18, 123464678, "paolita@example.com", "aeiou", Role.USER);

        when(userService.crearUsuario(user)).thenReturn(user);

        ResponseEntity<UserDto> response = userController.crearUsuario(user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Paola", response.getBody().getNombre());
    }

    @Test
    public void modificarUsuario() {
        UserDto user = new UserDto("1", "Paola", 18, 123464678, "paolita@example.com", "aeiou", Role.USER);
        UserDto updatedUser = new UserDto("1", "Ramos", 18, 123464678, "ramos@example.com", "aeiou", Role.USER);

        when(userService.modificarUsuario(user, "1")).thenReturn(updatedUser);

        ResponseEntity<UserDto> response = userController.modificarUsuario(user, "1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Ramos", response.getBody().getNombre());
    }

    @Test
    public void eliminarUsuario() {
        // No hay retorno esperado para eliminarUsuario, solo verificar que el servicio es llamado
        doNothing().when(userService).eliminarUsuario("1");
        ResponseEntity<Void> response = userController.eliminarUsuario("1");
        verify(userService, times(1)).eliminarUsuario("1");
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void mostrarListaDeUsuariosVacia() {
        when(userService.mostrarTodo()).thenReturn(Arrays.asList());
        ResponseEntity<List<UserDto>> response = userController.mostrarTodo();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, response.getBody().size());
    }

    @Test
    public void buscarUsuarioInexistentePorId() {
        when(userService.buscarPorId("999")).thenReturn(null);
        ResponseEntity<UserDto> response = userController.buscarPorId("999");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    public void modificarUsuarioInexistente() {
        UserDto user = new UserDto("", "", 0, 0, "", "", null);
        UserDto updatedUser = new UserDto("", "", 0, 0, "", "", null);

        when(userService.modificarUsuario(user, "1")).thenReturn(updatedUser);

        ResponseEntity<UserDto> response = userController.modificarUsuario(user, "1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("", response.getBody().getNombre());
    }
}
