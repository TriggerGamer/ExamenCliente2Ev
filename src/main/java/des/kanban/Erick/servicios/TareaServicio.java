package des.kanban.Erick.servicios;

import des.kanban.Erick.entidades.Tarea;

public interface TareaServicio {

	public Tarea obtenerTareaPorId(int idTarea);
	
	public Tarea crearTarea(int idProyecto, Tarea tarea, String Empleado);
	
	public Tarea modificarTarea(int idTarea, String NombreTarea, String Prioridad, String Empleado, String Estado, String Descripcion);
	
}
