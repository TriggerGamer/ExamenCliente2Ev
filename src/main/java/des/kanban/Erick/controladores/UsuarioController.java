package des.kanban.Erick.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import des.kanban.Erick.entidades.Usuario;
import des.kanban.Erick.servicios.UsuarioServicio;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

	@Autowired
	private UsuarioServicio servicio;
	
	@GetMapping("/alta")
	public ModelAndView getAlta () {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("usuario/alta");
		
		return mav;
	}
	
	@PostMapping("/crear")
	public String postAlta (@RequestParam String usuario, String password) {
		
		Usuario usuario1 = new Usuario();
		
		usuario1.setNombreUsuario(usuario);
		usuario1.setPassword(password);
		
		servicio.crearUsuario(usuario1);
		
		return "redirect:/login";
	}
	
	@GetMapping("/logout")
	public String logout () {
		
		return "";
	}

	@ResponseBody
	@GetMapping("/obtener")
	public String getLogin (HttpSession session) {
		
		String nombre = (String) session.getAttribute("user");
		
		return nombre;
	}

	
}
