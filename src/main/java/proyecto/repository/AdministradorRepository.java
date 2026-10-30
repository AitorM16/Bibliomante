package proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyecto.entity.Administrador;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {
}
