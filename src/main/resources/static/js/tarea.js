function Tarea(){
	this.id_tarea = "";
	this.titulo = "";
	this.descripcion = "";
	this.nombreUsuario = "";
	this.prioridad = "";
	this.estado = "";
}

function Tarea(id_tarea, titulo, descripcion, nombreUsuario, prioridad, estado){
	this.id_tarea = id_tarea;
	this.titulo = titulo;
	this.descripcion = descripcion;
	this.nombreUsuario = nombreUsuario;
	this.prioridad = prioridad;
	this.estado = estado;
}