package proyecto.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.entity.Estado;
import proyecto.entity.Prestamo;
import proyecto.repository.PrestamoRepository;

@Service
public class PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;
    
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
