package proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyecto.entity.Bibliotecario;

@Repository
public interface BibliotecarioRepository extends JpaRepository<Bibliotecario, Integer> {
}
