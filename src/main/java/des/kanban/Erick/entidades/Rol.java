package des.kanban.Erick.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "rol")
public class Rol implements Serializable {

	private static final long serialVersionUID = 653511970432101129L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id_rol")
	private int id_rol;
	
	@Column(name = "nombre_rol")
	private String nombre_rol;
	
	@ManyToMany(mappedBy = "roles")
	private List<Usuario> usuarios = new ArrayList<>();
	
	public Rol() {
		super();
	}

	public Rol(int id_rol, String nombre_rol, List<Usuario> usuarios) {
		super();
		this.id_rol = id_rol;
		this.nombre_rol = nombre_rol;
		this.usuarios = usuarios;
	}

	public int getId_rol() {
		return id_rol;
	}

	public void setId_rol(int id_rol) {
		this.id_rol = id_rol;
	}

	public String getNombre_rol() {
		return nombre_rol;
	}

	public void setNombre_rol(String nombre_rol) {
		this.nombre_rol = nombre_rol;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public void addUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
		usuario.getRoles().add(this);
	}
}
