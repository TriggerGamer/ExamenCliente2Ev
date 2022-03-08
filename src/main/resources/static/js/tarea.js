function Tarea(){
	this.id_tarea = "";
	this.titulo = "";
	this.descripcion = "";
	this.usuario = "";
	this.prioridad = "";
	this.estado = "";
}

function Tarea(id_tarea, titulo, descripcion, usuario, prioridad, estado){
	this.id_tarea = id_tarea;
	this.titulo = titulo;
	this.descripcion = descripcion;
	this.usuario = usuario;
	this.prioridad = prioridad;
	this.estado = estado;
}