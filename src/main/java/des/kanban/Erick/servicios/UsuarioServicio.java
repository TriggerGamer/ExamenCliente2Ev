package des.kanban.Erick.servicios;

import java.util.List;

import des.kanban.Erick.entidades.Usuario;

public interface UsuarioServicio {

	public Usuario obtenerUsuarioPorId(int idUsuario);
	
	public Usuario obtenerUsuarioPorNombre(String nombreUsuario);
	
	public List<Usuario> obtenerTodosLosUsuarios();
	
	public Usuario crearUsuario(Usuario usuario);
	
}
