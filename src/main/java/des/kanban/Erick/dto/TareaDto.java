package des.kanban.Erick.dto;

public class TareaDto {
	
	private int id_tarea;
	
	private String titulo;
	
	private String descripcion;
	
	private String nombreUsuario;
	
	private String prioridad;
	
	private String estado;

	public TareaDto() {
		super();
	}

	public TareaDto(int id_tarea, String titulo, String descripcion, String nombreUsuario, String prioridad,
			String estado) {
		super();
		this.id_tarea = id_tarea;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.nombreUsuario = nombreUsuario;
		this.prioridad = prioridad;
		this.estado = estado;
	}

	public int getId_tarea() {
		return id_tarea;
	}

	public void setId_tarea(int id_tarea) {
		this.id_tarea = id_tarea;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	
}
