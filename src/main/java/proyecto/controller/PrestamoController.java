package proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import proyecto.controller.dto.PrestamoResponse;
import proyecto.controller.dto.SolicitarPrestamoRequest;
import proyecto.services.PrestamoService;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    @PostMapping
    public ResponseEntity<PrestamoResponse> solicitarPrestamo(
            @Valid @RequestBody SolicitarPrestamoRequest solicitud) {
        return ResponseEntity.status(HttpStatus.CREATED).body(prestamoService.solicitar(solicitud));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PrestamoResponse>> consultarPrestamos(@PathVariable int usuarioId) {
        return ResponseEntity.ok(prestamoService.findByUsuario(usuarioId));
    }
}
