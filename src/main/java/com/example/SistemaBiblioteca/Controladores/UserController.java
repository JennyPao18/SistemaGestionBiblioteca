package com.example.SistemaBiblioteca.Controladores;

import com.example.SistemaBiblioteca.Dtos.UserDto;
import com.example.SistemaBiblioteca.Servicios.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> mostrarTodo(){
        List<UserDto> lista = userService.mostrarTodo();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> buscarPorId(@PathVariable String id){
        UserDto usuario = userService.buscarPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<UserDto> crearUsuario(@RequestBody UserDto usuario){
        UserDto saved = userService.crearUsuario(usuario);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> modificarUsuario(@RequestBody UserDto usuario, @PathVariable String id){
        UserDto update = userService.modificarUsuario(usuario, id);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable("id") String id){
        userService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
