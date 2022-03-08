package des.kanban.Erick.modelo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import des.kanban.Erick.entidades.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer>{

}
