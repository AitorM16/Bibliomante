package proyecto.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyecto.entity.Estado;
import proyecto.entity.Libro;
import proyecto.entity.Prestamo;
import proyecto.entity.Estado;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {

    java.util.List<Prestamo> findByUsuario_Id(Integer usuarioId);

    long countByUsuario_IdAndEstadoPrestamo(Integer usuarioId, Estado estadoPrestamo);
	
	//4a
	boolean existsByLibroAndEstadoPrestamoIn(Libro libro, List<Estado> estados);
	
	long countByLibroAndEstadoPrestamoIn(Libro libro, List<Estado> estados);
	
	//4b
	List<Prestamo> findByEstadoPrestamo(Estado estado);
	
	List<Prestamo> findByEstadoPrestamoAndFechaPrevistaDevolucionBefore(
	        Estado estado,
	        LocalDate fecha
	);
	//4c
	List<Prestamo> findByUsuario_Id(int usuarioId);
}
