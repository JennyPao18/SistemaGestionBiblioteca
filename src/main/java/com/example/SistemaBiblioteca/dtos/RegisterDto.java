package com.example.SistemaBiblioteca.dtos;

public class RegisterDto {

    private String nombre;
    private int edad;
    private int noTel;
    private String correo;
    private String contraseña;

    public RegisterDto(String nombre, int edad, int noTel, String correo, String contraseña) {
        this.nombre = nombre;
        this.edad = edad;
        this.noTel = noTel;
        this.correo = correo;
        this.contraseña = contraseña;
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
}
