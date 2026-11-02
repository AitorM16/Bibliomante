package proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyecto.entity.Prestamo;
import proyecto.entity.Estado;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {

    java.util.List<Prestamo> findByUsuario_Id(Integer usuarioId);

    long countByUsuario_IdAndEstadoPrestamo(Integer usuarioId, Estado estadoPrestamo);
}
