package proyecto.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import proyecto.controller.dto.PrestamoResponse;
import proyecto.controller.dto.SolicitarPrestamoRequest;
import proyecto.entity.Estado;
import proyecto.entity.Libro;
import proyecto.entity.Prestamo;
import proyecto.entity.Usuario;
import proyecto.repository.LibroRepository;

import proyecto.entity.Estado;
import proyecto.entity.Prestamo;
import proyecto.repository.PrestamoRepository;
import proyecto.repository.UsuarioRepository;

@Service
public class PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AdministradorService administradorService;

    @Transactional
    public PrestamoResponse solicitar(SolicitarPrestamoRequest solicitud) {
        Usuario usuario = usuarioRepository.findById(solicitud.usuarioId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        Libro libro = libroRepository.findById(solicitud.libroId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Libro no encontrado"));

        LocalDate hoy = LocalDate.now();
        if (!solicitud.fechaPrevistaDevolucion().isAfter(hoy)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "La fecha prevista de devolución debe ser posterior a hoy");
        }
        if (libro.getEjemplaresDisponibles() == null || libro.getEjemplaresDisponibles() <= 0) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "No hay ejemplares disponibles");
        }
        if (prestamoRepository.countByUsuario_IdAndEstadoPrestamo(usuario.getId(), Estado.ACTIVO)
                >= administradorService.getMaxPrestamos()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El usuario ha alcanzado el máximo de préstamos activos");
        }

        Prestamo prestamo = new Prestamo(
                hoy, solicitud.fechaPrevistaDevolucion(), Estado.ACTIVO, usuario, libro);
        libro.setEjemplaresDisponibles(libro.getEjemplaresDisponibles() - 1);
        libroRepository.save(libro);
        return PrestamoResponse.from(prestamoRepository.save(prestamo));
    }

    @Transactional(readOnly = true)
    public List<PrestamoResponse> findByUsuario(int usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }
        return prestamoRepository.findByUsuario_Id(usuarioId).stream()
                .map(PrestamoResponse::from)
                .toList();
    }
    
    //4b
    public List<Prestamo> findAll() {
        return prestamoRepository.findAll();
    }
    
    public boolean aprobar(int id) {
        Optional<Prestamo> prestamoOpt = prestamoRepository.findById(id);

        if (prestamoOpt.isEmpty() ||
                prestamoOpt.get().getEstado() != Estado.PENDIENTE) {
            return false;
        }

        Prestamo prestamo = prestamoOpt.get();
        prestamo.setEstado(Estado.ACTIVO);
        prestamoRepository.save(prestamo);
        return true;
    }
    
    public boolean marcarComoDevuelto(int id) {
        Optional<Prestamo> prestamoOpt = prestamoRepository.findById(id);

        if (prestamoOpt.isEmpty() || prestamoOpt.get().getEstado() != Estado.ACTIVO) {
            return false;
        }

        Prestamo prestamo = prestamoOpt.get();
        prestamo.setEstado(Estado.DEVUELTO);
        prestamoRepository.save(prestamo);
        return true;
    }
    
    
    
    public List<Prestamo> findPendientes() {
        return prestamoRepository.findByEstadoPrestamo(Estado.PENDIENTE);
    }
    
    public int actualizarVencidos() {
        List<Prestamo> vencidos =
                prestamoRepository.findByEstadoPrestamoAndFechaPrevistaDevolucionBefore(
                        Estado.ACTIVO,
                        LocalDate.now()
                );

        for (Prestamo prestamo : vencidos) {
            prestamo.setEstado(Estado.VENCIDO);
        }

        prestamoRepository.saveAll(vencidos);
        return vencidos.size();
    }
    
    //4c
    public List<Prestamo> historialPorUsuario(int usuarioId) {
        return prestamoRepository.findByUsuario_Id(usuarioId);
    }
    
    
    
}
