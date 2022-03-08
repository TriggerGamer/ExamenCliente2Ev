package des.kanban.Erick.servicios;

import java.util.List;

import des.kanban.Erick.entidades.Proyecto;

public interface ProyectoServicio {

	public Proyecto obtenerProyectoPorId(int idProyecto);
	
	public Proyecto crearProyecto(Proyecto proyecto, String empleados);
	
	public Boolean  borrarProyecto(int idProyecto);
	
	public List<Proyecto> obtenerProyectos();
	
	List<Proyecto> obtenerProyectosPorNombre(String nombreProyecto);
}