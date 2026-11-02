package proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyecto.entity.Evento;
import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {

    List<Evento> findByFechaHoraAfter(LocalDateTime fechaHora);
	
	boolean existsByIdAndUsuariosInscritosIsNotEmpty(int id);
	
}
