package com.example.SistemaBiblioteca.Dtos;

import com.example.SistemaBiblioteca.Extra.Role;

public class UserDto {

    private String id;
    private String nombre;
    private int edad;
    private int noTel;
    private String correo;
    private String contraseña;
    private Role role;

    public UserDto(String id, String nombre, int edad, int noTel, String correo, String contraseña, Role role) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.noTel = noTel;
        this.correo = correo;
        this.contraseña = contraseña;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getNoTel() {
        return noTel;
    }

    public void setNoTel(int noTel) {
        this.noTel = noTel;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
