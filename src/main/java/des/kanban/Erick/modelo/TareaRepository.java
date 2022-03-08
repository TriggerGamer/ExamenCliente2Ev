package des.kanban.Erick.modelo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import des.kanban.Erick.entidades.Tarea;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Integer>{

}
