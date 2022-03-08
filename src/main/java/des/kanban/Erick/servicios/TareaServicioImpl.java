package des.kanban.Erick.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import des.kanban.Erick.entidades.Proyecto;
import des.kanban.Erick.entidades.Tarea;
import des.kanban.Erick.entidades.Usuario;
import des.kanban.Erick.modelo.ProyectoRepository;
import des.kanban.Erick.modelo.TareaRepository;
import des.kanban.Erick.modelo.UsuarioRepository;

@Transactional
@Service
public class TareaServicioImpl implements TareaServicio{
	
	@Autowired
	private TareaRepository tareaRepo;
	
	@Autowired
	private ProyectoRepository proyectoRepo;
	
	@Autowired
	private UsuarioRepository usuarioRepo;

	@Override
	public Tarea obtenerTareaPorId(int idTarea) {
		
		return tareaRepo.getById(idTarea);
	}

	@Override
	public Tarea crearTarea(int idProyecto, Tarea tarea, String Empleado) {
		
		Proyecto proyecto = proyectoRepo.getById(idProyecto);
		
		int idUsuario = Integer.parseInt(Empleado);
		
		Usuario u = usuarioRepo.getById(idUsuario);
		
		proyecto.addTareas(tarea);
		u.anadirTarea(tarea);
		
		return tareaRepo.save(tarea);
	}

	@Override
	public Tarea modificarTarea(int idTarea, String NombreTarea, String Prioridad, String Empleado, String Estado, String Descripcion) {
		
		Tarea tarea = tareaRepo.getById(idTarea);
		
		tarea.setTitulo(NombreTarea);
		tarea.setDescripcion(Descripcion);
		tarea.setPrioridad(Prioridad);
		tarea.setEstado(Estado);
		
		Usuario u = usuarioRepo.findBynombreUsuario(Empleado);
		
		u.anadirTarea(tarea);
				
		return tarea;
	}

}
