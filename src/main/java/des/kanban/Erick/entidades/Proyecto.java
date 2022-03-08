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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "proyecto")
public class Proyecto implements Serializable{

	private static final long serialVersionUID = -8632886240874437109L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id_proyecto")
	private int id_proyecto;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@OneToMany( mappedBy = "proyecto",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private List<Tarea> tareas = new ArrayList<>();
	
	@ManyToMany(mappedBy = "proyectos")
	private List<Usuario> trabajadores = new ArrayList<>();

	public Proyecto() {
		super();
	}

	public Proyecto(int id_proyecto, String nombre, String descripcion, List<Tarea> tareas,
			List<Usuario> trabajadores) {
		super();
		this.id_proyecto = id_proyecto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tareas = tareas;
		this.trabajadores = trabajadores;
	}

	public int getId_proyecto() {
		return id_proyecto;
	}

	public void setId_proyecto(int id_proyecto) {
		this.id_proyecto = id_proyecto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(List<Tarea> tareas) {
		this.tareas = tareas;
	}

	public List<Usuario> getTrabajadores() {
		return trabajadores;
	}

	public void setTrabajadores(List<Usuario> trabajadores) {
		this.trabajadores = trabajadores;
	}
	
	public void addUsuario(Usuario usuario) {
		this.trabajadores.add(usuario);
		usuario.getProyectos().add(this);
	}
	
	public void addTareas(Tarea tarea) {
		this.tareas.add(tarea);
		tarea.setProyecto(this);
	}

}
