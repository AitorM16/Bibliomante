package proyecto.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyecto.controller.dto.EventoDisponibleResponse;
import proyecto.controller.dto.InscripcionEventoRequest;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;
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
    
    //4d
 // Crear un evento
    @PostMapping
    public ResponseEntity<Evento> crearEvento(@RequestBody Evento evento) {
        Evento nuevo = eventoService.guardar(evento);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }
 // Modificar un evento solo si no tiene usuarios inscritos
    @PutMapping("/{id}")
    public ResponseEntity<Void> modificarEvento(
            @PathVariable int id,
            @RequestBody Evento datosEvento) {

        Optional<Evento> eventoExistente = eventoService.findById(id);

        if (eventoExistente.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        boolean modificado = eventoService.modificar(id, datosEvento);

        if (!modificado) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
 // Eliminar un evento si no tiene usuarios inscritos
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEvento(@PathVariable int id) {
        Optional<Evento> eventoExistente = eventoService.findById(id);

        if (eventoExistente.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        boolean eliminado = eventoService.eliminar(id);

        if (!eliminado) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}




































