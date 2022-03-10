package des.kanban.Erick.dto;

public class UsuarioDto {

	private int idUsuario;

	private String nombreUsuario;

	public UsuarioDto() {
		super();
	}

	public UsuarioDto(int idUsuario, String nombreUsuario) {
		super();
		this.idUsuario = idUsuario;
		this.nombreUsuario = nombreUsuario;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	
}
