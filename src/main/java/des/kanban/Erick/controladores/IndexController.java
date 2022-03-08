package des.kanban.Erick.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import des.kanban.Erick.entidades.Proyecto;
import des.kanban.Erick.entidades.Usuario;
import des.kanban.Erick.servicios.ProyectoServicio;
import des.kanban.Erick.servicios.UsuarioServicio;


@Controller
public class IndexController {
	
	@Autowired
	private ProyectoServicio proyectoServicio;
	
	@Autowired
	private UsuarioServicio usuarioServicio;

	@GetMapping(value={"/index","/"})
	public ModelAndView getIndex (Model modelo) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
				
		getThingsIndex(modelo);
		
		return mav;
	}

	// Muy útil esta linea internet es maravilloso
	@PreAuthorize("isAuthenticated()")
	public void getThingsIndex (Model modelo) {
		List<Proyecto> proyectos = proyectoServicio.obtenerProyectos();
		modelo.addAttribute("proyectos", proyectos);
		
		List<Usuario> usuarios = usuarioServicio.obtenerTodosLosUsuarios();
		modelo.addAttribute("empleados", usuarios);
	}
	
	@GetMapping("/login")
	public ModelAndView getLogin () {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		
		return mav;
	}
	
}
