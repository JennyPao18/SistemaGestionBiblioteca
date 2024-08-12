package com.example.SistemaBiblioteca.Servicios;

import com.example.SistemaBiblioteca.Dtos.UserDto;
import com.example.SistemaBiblioteca.Entidades.UserEntity;
import com.example.SistemaBiblioteca.Repositorios.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServiceMethods{

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> mostrarTodo() {
        List<UserEntity> listaUsuarios = userRepository.findAll();
        return listaUsuarios.stream().map(this::convertirADto).collect(Collectors.toList());
    }

    @Override
    public UserDto buscarPorId(String id) {
        UserEntity usuario = userRepository.findById(id).orElse(null);
        return convertirADto(usuario);
    }

    @Override
    public UserDto crearUsuario(UserDto usuario) {
        validarUsuarioTipoDto(usuario);
        UserEntity userEntity = convertirAEntidad(usuario);
        userEntity = userRepository.save(userEntity);
        return convertirADto(userEntity);
    }

    @Override
    public UserDto modificarUsuario(UserDto usuario, String id) {
        validarUsuarioTipoDto(usuario);
        Optional<UserEntity> existingUserOptional = userRepository.findById(id);
        if (existingUserOptional.isPresent()) {
            UserEntity usuarioExistente = existingUserOptional.get();
            actualizarDetallesDeUsuario(usuarioExistente, usuario);
            UserEntity updatedUser = userRepository.save(usuarioExistente);
            return convertirADto(updatedUser);
        } else {
            return null;
        }
    }

    @Override
    public void eliminarUsuario(String id) {
        userRepository.deleteById(id);
        System.out.println("Usuario eliminado exitosamente");
    }

    public void eliminarTodosUsuarios(){
        userRepository.deleteAll();
    }

    public UserDto buscarPorCorreo (String correo) {
        Optional<UserEntity> usuario = userRepository.findByCorreo(correo);
        return usuario.map(this::convertirADto).orElse(null);
    }

    public void validarUsuarioTipoDto (UserDto userDto) {
        if (userDto == null || userDto.getNombre() == null || userDto.getCorreo() == null) {
            System.out.println("Debe de indicar su nombre de usuario y su correo");
        }
    }

    private UserEntity convertirAEntidad(UserDto userDto) {
        return new UserEntity(
                userDto.getId(),
                userDto.getNombre(),
                userDto.getEdad(),
                userDto.getNoTel(),
                userDto.getCorreo(),
                userDto.getContraseña(),
                userDto.getRole()
        );
    }

    private UserDto convertirADto(UserEntity usuario) {
        if (usuario == null) return null;
        return new UserDto(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEdad(),
                usuario.getNoTel(),
                usuario.getCorreo(),
                usuario.getContraseña(),
                usuario.getRole()
        );
    }

    private void actualizarDetallesDeUsuario(UserEntity usuarioExistente, UserDto usuario) {
        usuarioExistente.setNombre(usuario.getNombre());
        usuarioExistente.setEdad(usuario.getEdad());
        usuarioExistente.setNoTel(usuario.getNoTel());
        usuarioExistente.setCorreo(usuario.getCorreo());
        usuarioExistente.setContraseña(usuario.getContraseña());
        usuarioExistente.setRole(usuario.getRole());
    }
}
