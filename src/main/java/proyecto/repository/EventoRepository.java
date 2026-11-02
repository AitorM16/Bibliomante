package proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyecto.entity.Evento;


@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {
	
	boolean existsByIdAndUsuariosInscritosIsNotEmpty(int id);
	
}
