package des.kanban.Erick.controladores;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import des.kanban.Erick.dto.TareaDto;
import des.kanban.Erick.entidades.Tarea;
import des.kanban.Erick.entidades.Usuario;
import des.kanban.Erick.servicios.TareaServicio;
import des.kanban.Erick.servicios.UsuarioServicio;

@Controller
@RequestMapping("tarea")
public class TareaController {
	
	@Autowired
	private UsuarioServicio usuarioServicio;
	
	@Autowired
	private TareaServicio servicioTarea;
	
	@GetMapping("/{idTarea}")
	public ModelAndView getTarea (@PathVariable int idTarea, Model modelo) {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("tarea/perfilTarea");
		
		Tarea tarea = servicioTarea.obtenerTareaPorId(idTarea);
		modelo.addAttribute("tarea", tarea);
		
		Usuario empleado = tarea.getUsuario();
		modelo.addAttribute("empleado", empleado);
		
		List<Usuario> empleados = usuarioServicio.obtenerTodosLosUsuarios();
		modelo.addAttribute("empleados", empleados);
		
		return mav;
	}
	
	@ResponseBody
	@PostMapping("/crear/{idProyecto}")
	public ResponseEntity<Object> crearTarea (@PathVariable int idProyecto, @RequestBody Map<String, String> json) {
		
		Tarea tarea = new Tarea();
		
		tarea.setTitulo(json.get("titulo"));
		tarea.setDescripcion(json.get("descripcion"));
		tarea.setPrioridad(json.get("prioridad"));
		tarea.setEstado(json.get("estado"));
		
		Tarea tarea2 = servicioTarea.crearTarea(idProyecto, tarea, json.get("empleado"));
		
		TareaDto tarea3 = new TareaDto(tarea2.getId_tarea(), tarea2.getTitulo(), tarea2.getDescripcion(), tarea2.getUsuario().getNombreUsuario(), tarea2.getPrioridad(), tarea2.getEstado());
		
		return new ResponseEntity<Object>(tarea3, HttpStatus.OK);
	}
	
	@ResponseBody
	@PostMapping("/modificar/{idTarea}")
	public ResponseEntity<Object> postmodificarTarea (@PathVariable int idTarea, @RequestBody Map<String, String> json) {
		
		String Nombre = json.get("titulo");
		String Prioridad = json.get("prioridad");
		String Trabajador = json.get("empleado");
		String Estado = json.get("estado"); 
		String Descripcion = json.get("descripcion");
		
		Tarea tarea = servicioTarea.modificarTarea(idTarea, Nombre, Prioridad, Trabajador, Estado, Descripcion);
		
		TareaDto tarea2 = new TareaDto();
		
		return new ResponseEntity<Object>(tarea2, HttpStatus.OK);
	}

	// Métodos para borrar tareas
	@ResponseBody
	@GetMapping("/borrar/{id_tarea}")
	public ResponseEntity<Object> borrarTarea(@PathVariable int id_tarea) {

		// Borrar los datos
		servicioTarea.borrarTarea(id_tarea);
		
		return  new ResponseEntity<Object>("true", HttpStatus.OK);;
	}
}
