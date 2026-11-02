package proyecto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.entity.Estado;
import proyecto.entity.Libro;
import proyecto.repository.LibroRepository;
import proyecto.repository.PrestamoRepository;

@Service
public class LibroService {

	@Autowired
	private LibroRepository libroRepository;

	public List<Libro> findAll() {
		return libroRepository.findAll();
	}

	public Optional<Libro> findById(int id) {
		return libroRepository.findById(id);
	}
	
	
	
	//4a

	public Libro guardar(Libro datosLibro) {
		
		 return libroRepository.save(datosLibro);
	}
	
	@Autowired
	private PrestamoRepository prestamoRepository;
	
	public boolean eliminar(int id) {
	    Optional<Libro> libroOpt = libroRepository.findById(id);

	    if (libroOpt.isEmpty()) {
	        return false;
	    }

	    Libro libro = libroOpt.get();

	    boolean tienePrestamosPendientes =
	            prestamoRepository.existsByLibroAndEstadoPrestamoIn(
	                    libro,
	                    List.of(Estado.ACTIVO, Estado.VENCIDO)
	            );

	    if (tienePrestamosPendientes) {
	        return false;
	    }

	    libroRepository.delete(libro);
	    return true;
	}
	
	//método para comprobar que el nuevo número de ejemplares no sea menor que los préstamos activos o vencidos.
	public boolean ejemplaresValidos(int id, int nuevosEjemplares) {
	    Optional<Libro> libroOpt = libroRepository.findById(id);

	    if (libroOpt.isEmpty()) {
	        return false;
	    }

	    long prestamosPendientes =
	            prestamoRepository.countByLibroAndEstadoPrestamoIn(
	                    libroOpt.get(),
	                    List.of(Estado.ACTIVO, Estado.VENCIDO)
	            );

	    return nuevosEjemplares >= prestamosPendientes;
	}

	

	
}
