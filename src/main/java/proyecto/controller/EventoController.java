package proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyecto.controller.dto.EventoDisponibleResponse;
import proyecto.controller.dto.InscripcionEventoRequest;
import proyecto.entity.Evento;
import proyecto.services.EventoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;
    
 // 1.b Consultar eventos disponibles
    @GetMapping
    public ResponseEntity<List<Evento>> listarEventos() {
        List<Evento> eventos = eventoService.findAll();
        return new ResponseEntity<>(eventos, HttpStatus.OK);
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<EventoDisponibleResponse>> listarEventosDisponibles() {
        return ResponseEntity.ok(eventoService.findDisponibles());
    }

    @PostMapping("/{eventoId}/inscripciones")
    public ResponseEntity<Void> inscribir(
            @PathVariable int eventoId,
            @Valid @RequestBody InscripcionEventoRequest solicitud) {
        eventoService.inscribir(eventoId, solicitud.usuarioId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{eventoId}/inscripciones/{usuarioId}")
    public ResponseEntity<Void> cancelarInscripcion(
            @PathVariable int eventoId,
            @PathVariable int usuarioId) {
        eventoService.cancelarInscripcion(eventoId, usuarioId);
        return ResponseEntity.noContent().build();
    }
}
