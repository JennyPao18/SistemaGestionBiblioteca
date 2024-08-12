package com.example.SistemaBiblioteca.Servicios;

import com.example.SistemaBiblioteca.Dtos.UserDto;
import java.util.List;

public interface UserServiceMethods {
    List<UserDto> mostrarTodo ();

    UserDto buscarPorId (String id);

    UserDto crearUsuario (UserDto usuario);

    UserDto modificarUsuario (UserDto usuario, String id);

    void eliminarUsuario (String id);
}
