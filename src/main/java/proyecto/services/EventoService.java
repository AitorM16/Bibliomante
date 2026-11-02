package proyecto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.entity.Evento;
import proyecto.repository.EventoRepository;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;
    
    public List<Evento> findAll() {
        return eventoRepository.findAll();
    }
    
    
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
