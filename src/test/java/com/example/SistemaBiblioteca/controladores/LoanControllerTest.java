package com.example.SistemaBiblioteca.controladores;

import com.example.SistemaBiblioteca.entidades.LoanEntity;
import com.example.SistemaBiblioteca.servicios.LoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class LoanControllerTest {

    @Mock
    private LoanService loanService;

    @InjectMocks
    private LoanController loanController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void mostrarListaDePrestamos() {
        LoanEntity loan1 = new LoanEntity("1", "1", "1", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 15), true);
        LoanEntity loan2 = new LoanEntity("2", "2", "2", LocalDate.of(2024, 2, 1), LocalDate.of(2024, 2, 15), false);

        List<LoanEntity> loans = Arrays.asList(loan1, loan2);
        when(loanService.mostrarTodosPrestamos()).thenReturn(loans);
        List<LoanEntity> response = loanController.mostrarTodosPrestamos();

        assertEquals(2, response.size());
        assertEquals("1", response.get(0).getId());
        assertEquals("2", response.get(1).getId());
    }

    @Test
    public void crearPrestamo() {
        LoanEntity loan = new LoanEntity("1", "1", "1", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 15), true);

        when(loanService.crearPrestamo(loan)).thenReturn(loan);
        LoanEntity response = loanController.crearPrestamo(loan);

        assertEquals("1", response.getId());
        assertEquals("1", response.getIdLibro());
        assertEquals("1", response.getIdUsuario());
        assertEquals(LocalDate.of(2024, 1, 1), response.getFechaPrestamo());
        assertEquals(LocalDate.of(2024, 1, 15), response.getFechaDevolucion());
    }

    @Test
    public void devolverPrestamo() {
        doNothing().when(loanService).devolverPrestamo("1");
        loanController.devolverPrestamo("1");
        verify(loanService, times(1)).devolverPrestamo("1");
    }

    @Test
    public void eliminarPrestamo() {
        doNothing().when(loanService).eliminarPrestamo("1");
        loanController.eliminarPrestamo("1");
        verify(loanService, times(1)).eliminarPrestamo("1");
    }

    @Test
    public void mostrarListaDePrestamosVacia() {
        when(loanService.mostrarTodosPrestamos()).thenReturn(Arrays.asList());
        List<LoanEntity> response = loanController.mostrarTodosPrestamos();
        assertEquals(0, response.size());
    }

    @Test
    public void crearPrestamoInvalido() {
        LoanEntity loan = new LoanEntity("", "", "", null, null, false);

        when(loanService.crearPrestamo(loan)).thenReturn(loan);

        LoanEntity response = loanController.crearPrestamo(loan);

        assertEquals("", response.getId());
        assertEquals("", response.getIdLibro());
        assertEquals("", response.getIdUsuario());
        assertEquals(null, response.getFechaPrestamo());
        assertEquals(null, response.getFechaDevolucion());
        assertEquals(false, response.isDevuelto());
    }
}
