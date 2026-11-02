package proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyecto.entity.Prestamo;
import proyecto.services.PrestamoService;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;
    
 //4b Listar préstamos
    @GetMapping
    public ResponseEntity<List<Prestamo>> listarPrestamos() {
        List<Prestamo> prestamos = prestamoService.findAll();
        return new ResponseEntity<>(prestamos, HttpStatus.OK);

    }
 // Aprobar solicitud y pasarla a ACTIVO
    @PutMapping("/{id}/aprobar")
    public ResponseEntity<Void> aprobarPrestamo(@PathVariable int id) {
        boolean aprobado = prestamoService.aprobar(id);

        if (!aprobado) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    //4b
    @PutMapping("/{id}/devolver")
    public ResponseEntity<Void> marcarComoDevuelto(@PathVariable int id) {
        boolean actualizado = prestamoService.marcarComoDevuelto(id);

        if (!actualizado) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/actualizar-vencidos")
    public ResponseEntity<String> actualizarVencidos() {
        int cantidad = prestamoService.actualizarVencidos();

        return new ResponseEntity<>(
                "Préstamos actualizados a VENCIDO: " + cantidad,
                HttpStatus.OK
        );
    }
    
    //4c
 // Historial de préstamos de un usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Prestamo>> historialPorUsuario(
            @PathVariable int usuarioId) {
        List<Prestamo> historial = prestamoService.historialPorUsuario(usuarioId);
        return new ResponseEntity<>(historial, HttpStatus.OK);
    }
    
}































