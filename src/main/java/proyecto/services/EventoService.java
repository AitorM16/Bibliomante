package proyecto.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import proyecto.controller.dto.EventoDisponibleResponse;
import proyecto.entity.Evento;
import proyecto.entity.Usuario;
import proyecto.repository.EventoRepository;
import proyecto.repository.UsuarioRepository;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Transactional(readOnly = true)
    public List<Evento> findAll() {
        return eventoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<EventoDisponibleResponse> findDisponibles() {
        LocalDateTime ahora = LocalDateTime.now();
        return eventoRepository.findByFechaHoraAfter(ahora).stream()
                .filter(this::tieneAforo)
                .map(EventoDisponibleResponse::from)
                .toList();
    }

    @Transactional
    public void inscribir(int eventoId, int usuarioId) {
        Evento evento = buscarEvento(eventoId);
        Usuario usuario = buscarUsuario(usuarioId);
        if (!evento.getFechaHora().isAfter(LocalDateTime.now())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El evento ya ha comenzado");
        }

        List<Usuario> inscritos = inscritos(evento);
        if (inscritos.stream().anyMatch(inscrito -> inscrito.getId() == usuarioId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El usuario ya está inscrito en este evento");
        }
        if (!tieneAforo(evento)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El evento ha alcanzado su aforo máximo");
        }

        inscritos.add(usuario);
        eventoRepository.save(evento);
    }

    @Transactional
    public void cancelarInscripcion(int eventoId, int usuarioId) {
        Evento evento = buscarEvento(eventoId);
        buscarUsuario(usuarioId);
        List<Usuario> inscritos = inscritos(evento);
        boolean eliminado = inscritos.removeIf(inscrito -> inscrito.getId() == usuarioId);
        if (!eliminado) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario no está inscrito en este evento");
        }
        eventoRepository.save(evento);
    }

    private Evento buscarEvento(int eventoId) {
        return eventoRepository.findById(eventoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento no encontrado"));
    }

    private Usuario buscarUsuario(int usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
    }

    private List<Usuario> inscritos(Evento evento) {
        if (evento.getUsuariosInscritos() == null) {
            evento.setUsuariosInscritos(new ArrayList<>());
        }
        return evento.getUsuariosInscritos();
    }

    private boolean tieneAforo(Evento evento) {
        int numeroInscritos = evento.getUsuariosInscritos() == null ? 0 : evento.getUsuariosInscritos().size();
        return numeroInscritos < evento.getAforoMaximo();
    
    
    //4d
    public Evento guardar(Evento evento) {
        return eventoRepository.save(evento);
    }
    public boolean modificar(int id, Evento datosEvento) {
        Optional<Evento> eventoOpt = eventoRepository.findById(id);

        if (eventoOpt.isEmpty() ||
                eventoRepository.existsByIdAndUsuariosInscritosIsNotEmpty(id)) {
            return false;
        }

        datosEvento.setId(id);
        eventoRepository.save(datosEvento);
        return true;
    }
    
    public Optional<Evento> findById(int id) {
        return eventoRepository.findById(id);
    }
    
    public boolean eliminar(int id) {
        Optional<Evento> eventoOpt = eventoRepository.findById(id);

        if (eventoOpt.isEmpty() ||
                eventoRepository.existsByIdAndUsuariosInscritosIsNotEmpty(id)) {
            return false;
        }

        eventoRepository.delete(eventoOpt.get());
        return true;
    }
}
