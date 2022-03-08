package des.kanban.Erick.modelo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import des.kanban.Erick.entidades.Proyecto;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Integer>{

	@Query("SELECT p FROM Proyecto p WHERE p.nombre LIKE %:nombre% OR p.descripcion LIKE %:descripcion%")
	List<Proyecto> findByNombreOrDescripcion(@Param("nombre") String nombre, @Param("descripcion") String descripcion);
}
