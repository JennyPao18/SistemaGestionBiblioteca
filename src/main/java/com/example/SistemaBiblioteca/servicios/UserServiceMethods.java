package com.example.SistemaBiblioteca.servicios;

import com.example.SistemaBiblioteca.dtos.UserDto;
import java.util.List;

public interface UserServiceMethods {
    List<UserDto> mostrarTodo ();

    UserDto buscarPorId (String id);

    UserDto crearUsuario (UserDto usuario);

    UserDto modificarUsuario (UserDto usuario, String id);

    void eliminarUsuario (String id);
}
