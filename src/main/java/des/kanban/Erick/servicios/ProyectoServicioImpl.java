package des.kanban.Erick.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import des.kanban.Erick.entidades.Proyecto;
import des.kanban.Erick.entidades.Usuario;
import des.kanban.Erick.modelo.ProyectoRepository;
import des.kanban.Erick.modelo.UsuarioRepository;

@Transactional
@Service
public class ProyectoServicioImpl implements ProyectoServicio{

	@Autowired
	private ProyectoRepository proyectoRepo;
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Override
	public Proyecto obtenerProyectoPorId(int idProyecto) {
		return proyectoRepo.getById(idProyecto);
	}
	
	@Override
	public List<Proyecto> obtenerProyectosPorNombre(String nombreProyecto) {
		return proyectoRepo.findByNombreOrDescripcion(nombreProyecto, nombreProyecto);
	}

	@Override
	public Proyecto crearProyecto(Proyecto proyecto, String empleados) {
		
		String empleado[] = empleados.split(",");
		
		for(int i = 0; i < empleado.length; i ++){
			Usuario u = usuarioRepo.findBynombreUsuario(empleado[i]);
			
			u.anadirProyecto(proyecto);
		}		
		
		return proyectoRepo.save(proyecto);
	}

	@Override
	public Boolean borrarProyecto(int idProyecto) {
		
		try {
			proyectoRepo.deleteById(idProyecto);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	@Override
	public List<Proyecto> obtenerProyectos() {
		return proyectoRepo.findAll();
	}

}
