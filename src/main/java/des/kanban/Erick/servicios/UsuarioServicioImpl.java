package des.kanban.Erick.servicios;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import des.kanban.Erick.entidades.Rol;
import des.kanban.Erick.entidades.Usuario;
import des.kanban.Erick.modelo.RolRepository;
import des.kanban.Erick.modelo.UsuarioRepository;

@Transactional
@Service
public class UsuarioServicioImpl implements UsuarioServicio, UserDetailsService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	RolRepository rolRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;	
	
	@Override
	public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioRepository.findBynombreUsuario(username);
		
		List<Rol> roles = usuario.getRoles();
		
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		
		for(Rol rol : roles) {
			grantedAuthorities.add(new SimpleGrantedAuthority(rol.getNombre_rol()));
		}
		return new org.springframework.security.core.userdetails.User(usuario.getNombreUsuario(), usuario.getPassword(), grantedAuthorities);
	}

	/* @Override
	public void borrar(int idUsuario) {
		try {
			usuarioRepository.deleteById(idUsuario);
		} catch (Exception e) {
		}
	}*/

	@Override
	public Usuario obtenerUsuarioPorId(int idUsuario) {
		return usuarioRepository.getById(idUsuario);
	}

	@Override
	public Usuario obtenerUsuarioPorNombre(String nombreUsuario) {
		
		return usuarioRepository.findBynombreUsuario(nombreUsuario);
		
	}

	@Override
	public List<Usuario> obtenerTodosLosUsuarios() {	
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario crearUsuario(Usuario usuario) {
		
		usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
		
		usuarioRepository.save(usuario);
		
		Rol rol = rolRepository.getById(2);
		
		usuario.anadirRol(rol);
		
		return usuario;
	}

}
