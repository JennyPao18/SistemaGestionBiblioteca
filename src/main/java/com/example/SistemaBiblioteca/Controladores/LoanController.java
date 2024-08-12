package com.example.SistemaBiblioteca.Controladores;

import com.example.SistemaBiblioteca.Entidades.LoanEntity;
import com.example.SistemaBiblioteca.Servicios.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping
    public List<LoanEntity> mostrarTodosPrestamos() {
        return loanService.mostrarTodosPrestamos();
    }

    @PostMapping
    public LoanEntity crearPrestamo(@RequestBody LoanEntity loan) {
        return loanService.crearPrestamo(loan);
    }

    @PostMapping("/devolver/{id}")
    public void devolverPrestamo(@PathVariable String id) {
        loanService.devolverPrestamo(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarPrestamo(@PathVariable String id) {
        loanService.eliminarPrestamo(id);
    }
}
