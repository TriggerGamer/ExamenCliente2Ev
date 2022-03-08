package des.kanban.Erick.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 5715364617433807790L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private int id_usuario;
	
	@Column(name = "nombre_usuario")
	private String nombreUsuario;
	
	@Column(name = "contrasena")
	private String password;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
			@JoinTable(name = "usuario_rol",
			joinColumns = @JoinColumn(name = "id_usuario"),
			inverseJoinColumns = @JoinColumn(name = "id_rol"))
	private List<Rol> roles = new ArrayList<>();
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
			@JoinTable(name = "proyecto_usuario",
			joinColumns = @JoinColumn(name = "id_usuario"),
			inverseJoinColumns = @JoinColumn(name = "id_proyecto"))
	private List<Proyecto> proyectos = new ArrayList<>();
	
	@OneToMany( mappedBy = "usuario",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private List<Tarea> tareas = new ArrayList<>();

	public Usuario() {
		super();
	}

	public Usuario(int id_usuario, String nombreusuario, String password, List<Rol> roles, List<Proyecto> proyectos,
			List<Tarea> tareas) {
		super();
		this.id_usuario = id_usuario;
		this.nombreUsuario = nombreusuario;
		this.password = password;
		this.roles = roles;
		this.proyectos = proyectos;
		this.tareas = tareas;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public List<Proyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(List<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

	public List<Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(List<Tarea> tareas) {
		this.tareas = tareas;
	}
	
	public void anadirRol(Rol rol) {
	    rol.addUsuario(this);
	}
	
	public void anadirProyecto(Proyecto proyecto) {
	    proyecto.addUsuario(this);
	}
	
	public void anadirTarea(Tarea tarea) {
	    tarea.addUsuario(this);
	}
}
