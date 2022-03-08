package des.kanban.Erick.modelo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import des.kanban.Erick.entidades.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	@Query("from Usuario where nombre_usuario = :nombre")
	Usuario findBynombreUsuario(String nombre);
	
}
