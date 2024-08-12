package com.example.SistemaBiblioteca.Servicios;

import com.example.SistemaBiblioteca.Dtos.AuthenticationDto;
import com.example.SistemaBiblioteca.Dtos.LoginDto;
import com.example.SistemaBiblioteca.Dtos.RegisterDto;
import com.example.SistemaBiblioteca.Entidades.UserEntity;
import com.example.SistemaBiblioteca.Extra.Role;
import com.example.SistemaBiblioteca.JwtSeguridad.JwtService;
import com.example.SistemaBiblioteca.Repositorios.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationDto login(final LoginDto request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsuario(), request.getContraseña()));
        UserDetails user = userRepository.findByCorreo(request.getUsuario()).orElseThrow();
        String token = jwtService.obtenerToken(user);
        return new AuthenticationDto(token);
    }

    public AuthenticationDto register (final RegisterDto request) {
        UserEntity user = new UserEntity();
        user.setNombre(request.getNombre());
        user.setEdad(request.getEdad());
        user.setNoTel(request.getNoTel());
        user.setCorreo(request.getCorreo());
        user.setContraseña(passwordEncoder.encode(request.getContraseña()));
        user.setRole(Role.USER);

        userRepository.save(user);
        return new AuthenticationDto(this.jwtService.obtenerToken(user));
    }
}
