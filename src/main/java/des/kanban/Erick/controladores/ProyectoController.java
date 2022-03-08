package des.kanban.Erick.controladores;

import java.util.ArrayList;
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
import des.kanban.Erick.entidades.Proyecto;
import des.kanban.Erick.entidades.Tarea;
import des.kanban.Erick.entidades.Usuario;
import des.kanban.Erick.servicios.ProyectoServicio;
import des.kanban.Erick.servicios.TareaServicio;
import des.kanban.Erick.servicios.UsuarioServicio;

@Controller
@RequestMapping("proyecto")
public class ProyectoController {
	
	@Autowired
	private UsuarioServicio usuarioServicio;
	
	@Autowired
	private ProyectoServicio servicioProyecto;
	
	@Autowired
	private TareaServicio servicioTarea;
	
	@GetMapping("/{idProyecto}")
	public String getPerfilProyecto (@PathVariable int idProyecto, Model modelo) {
		
		Proyecto proyecto = servicioProyecto.obtenerProyectoPorId(idProyecto);
		modelo.addAttribute("proyecto", proyecto);
		
		List<Tarea> tareas = proyecto.getTareas();
		modelo.addAttribute("tareas", tareas);
		
		List<Usuario> empleados = proyecto.getTrabajadores();
		modelo.addAttribute("empleados", empleados);
		
		return "proyecto/TareasAjax";
	}
	
	@ResponseBody
	@GetMapping("/todas/{idProyecto}")
	public List<TareaDto> getTareasProyecto (@PathVariable int idProyecto) {
		
		Proyecto proyecto = servicioProyecto.obtenerProyectoPorId(idProyecto);
		
		List<Tarea> tareas = proyecto.getTareas();
		
		List<TareaDto> tareas2 = new ArrayList<>();
		
		for(Tarea tarea : tareas) {
			TareaDto tarea2 = new TareaDto();
			
			tarea2.setId_tarea(tarea.getId_tarea());
			tarea2.setTitulo(tarea.getTitulo());
			tarea2.setDescripcion(tarea.getDescripcion());
			tarea2.setNombreUsuario(tarea.getUsuario().getNombreUsuario());
			tarea2.setPrioridad(tarea.getPrioridad());
			tarea2.setEstado(tarea.getEstado());
			
			tareas2.add(tarea2);
 		}
		
		return tareas2;
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
	
	@PostMapping("/crear")
	public String crearProyecto (@RequestParam String nombreProyecto, String empleadosProyecto) {
		
		Proyecto proyecto = new Proyecto();
		proyecto.setNombre(nombreProyecto);
		
		servicioProyecto.crearProyecto(proyecto, empleadosProyecto);
		
		
		return "redirect:/";
	}
	
	@GetMapping("/borrar/{idProyecto}")
	public String borrarProyecto (@PathVariable int idProyecto) {
		
		servicioProyecto.borrarProyecto(idProyecto);
		
		return "redirect:/index";
	}
	
	@GetMapping("/buscar/{nombreProyecto}")
	public ModelAndView buscarProyecto (@PathVariable String nombreProyecto, @RequestParam String nombreProyecto1, Model modelo) {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		
		List<Proyecto> proyectos = servicioProyecto.obtenerProyectosPorNombre(nombreProyecto1);
		modelo.addAttribute("proyectos", proyectos);
		
		List<Usuario> usuarios = usuarioServicio.obtenerTodosLosUsuarios();
		modelo.addAttribute("empleados", usuarios);
		
		return mav;
	}
	
}
