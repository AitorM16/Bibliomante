package proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyecto.entity.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer> {
}
