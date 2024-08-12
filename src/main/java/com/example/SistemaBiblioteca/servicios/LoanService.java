package com.example.SistemaBiblioteca.servicios;

import com.example.SistemaBiblioteca.entidades.LoanEntity;
import com.example.SistemaBiblioteca.repositorios.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public List<LoanEntity> mostrarTodosPrestamos() {
        return loanRepository.findAll();
    }

    public LoanEntity buscarPrestamoPorId (String id) {
        return loanRepository.findById(id).orElse(null);
    }

    public LoanEntity crearPrestamo (LoanEntity prestamo) {
        prestamo.setFechaPrestamo(LocalDate.now());
        prestamo.setDevuelto(false);
        return loanRepository.save(prestamo);
    }

    public void devolverPrestamo(String id) {
        LoanEntity prestamo = loanRepository.findById(id).orElse(null);
        if (prestamo != null) {
            prestamo.setDevuelto(true);
            prestamo.setFechaDevolucion(LocalDate.now());
            loanRepository.save(prestamo);
        }
    }

    public void eliminarPrestamo (String id) {
        loanRepository.deleteById(id);
        System.out.println("Prestamo eliminado exitosamente");
    }
}
