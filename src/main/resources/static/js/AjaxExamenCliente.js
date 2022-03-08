document.addEventListener("DOMContentLoaded", function() {

	cargarTareasArray();
	$("#anadirTarea").click(crearTarea);
	$("#editarTarea").click(editarTarea);

});

// Declarar el array que contendra las tareas
var ArrayTareas = new Array();

// Cargar las tareas con ajax
function cargarTareasArray() {

	let enlace = window.location.href;
	let contenedor = enlace.split("/");
	let idProyecto = contenedor[4];

	var token = $("meta[name='_csrf']").attr("content");

	fetch("/proyecto/todas/" + idProyecto, {
		headers: { "Content-Type": "application/json; charset=utf-8", 'X-CSRF-TOKEN': token }, method: 'GET', credentials: 'same-origin'
	})
		.then(res => res.json())
		.then(response => {

			for (let tarea of response) {

				var tarea2 = new Tarea();

				tarea2.id_tarea = tarea.id_tarea;
				tarea2.titulo = tarea.titulo;
				tarea2.descripcion = tarea.descripcion;
				tarea2.usuario = tarea.nombreUsuario;
				tarea2.prioridad = tarea.prioridad;
				tarea2.estado = tarea.estado;

				ArrayTareas.push(tarea2);

			}

			pintarTareas(ArrayTareas);
			console.log(ArrayTareas);

		})

}

//Insertar cada tarea en el dom
function tareaDom(tarea) {

	let divCard = document.createElement("div");
	divCard.classList.add("card", "draggable", "shadow-sm");
	divCard.setAttribute("id", "Tarea" + tarea.id_tarea);
	divCard.setAttribute("draggable", "true");
	divCard.setAttribute("ondragstart", "drag(event)");

	let divCardBody = document.createElement("div");
	divCardBody.classList.add("card-body", "p-2");

	let divCardTitle = document.createElement("div");
	divCardTitle.classList.add("card-title");

	let span = document.createElement("span");
	if (tarea.prioridad == "BAJA") {
		span.classList.add("badge", "rounded-pill", "bg-success");
	}
	else if (tarea.prioridad == "MEDIA") {
		span.classList.add("badge", "rounded-pill", "bg-warning");
	}
	else {
		span.classList.add("badge", "rounded-pill", "bg-danger");
	}

	span.textContent = tarea.prioridad;
	let link = document.createElement("a");
	link.classList.add("lead", "font-weight-light");
	link.textContent = "Tarea " + tarea.id_tarea;

	let link2 = document.createElement("a");
	link2.setAttribute("style", "text-align: right; float: right;");
	link2.textContent = tarea.usuario;

	let descp = document.createElement("p");
	descp.textContent = tarea.descripcion;

	let button = document.createElement("button");
	button.classList.add("btn", "btn-primary", "btn-sm");
	button.textContent = "Ver Tarea";
	verTarea(button, tarea);

	divCardTitle.appendChild(span);
	divCardTitle.appendChild(link);
	divCardTitle.appendChild(link2);

	divCardBody.appendChild(divCardTitle);
	divCardBody.appendChild(descp);
	divCardBody.appendChild(button);

	divCard.appendChild(divCardBody);

	let divDropZone = document.createElement("div");
	divDropZone.classList.add("dropzone", "rounded");
	divDropZone.setAttribute("ondrop", "drop(event)");
	divDropZone.setAttribute("ondragover", "allowDrop(event)");
	divDropZone.setAttribute("ondragleave", "clearDrop(event)");

	let columna = document.getElementById(tarea.estado);
	columna.appendChild(divCard);
	columna.appendChild(divDropZone);
	
}

//Insertar todas las tareas en el dom
function pintarTareas(todasTareas) {
	for (let tarea of todasTareas) {
		tareaDom(tarea);
	}
	
	$('.draggable').on('dragend', despuesDeSoltar);

}

// Crear Tareas
function crearTarea(event) {

	event.preventDefault();

	let enlace = window.location.href;
	let contenedor = enlace.split("/");
	let idProyecto = contenedor[4];

	var token = $("meta[name='_csrf']").attr("content");

	let titulo = $('#inputNombre').val();
	if (titulo == null) {
		titulo = " ";
	}

	let descripcion = $('#inputDescripcion').val();
	if (descripcion == null) {
		descripcion = " ";
	}

	fetch("/tarea/crear/" + idProyecto, {
		headers: { "Content-Type": "application/json; charset=utf-8", 'X-CSRF-TOKEN': token }, method: 'POST',
		credentials: 'same-origin',
		body: JSON.stringify({ titulo: titulo, descripcion: descripcion, prioridad: $('#inputPrioridad').val(), estado: "Preparada", empleado: $('#inputTrabajador').val() })
	})
		.then(function(response) {
			
			if (response.ok) {
				return response.json();
			}
			else{
				alert("lo sentimos ha habido un error");
			}

		})
		.then(tarea => {

			// Agregar la tarea al array si todo esta bien
			var tarea2 = new Tarea();
			tarea2.id_tarea = tarea.id_tarea;
			tarea2.titulo = tarea.titulo;
			tarea2.descripcion = tarea.descripcion;
			tarea2.usuario = tarea.nombreUsuario;
			tarea2.prioridad = tarea.prioridad;
			tarea.estado = tarea.estado;

			ArrayTareas.push(tarea2);

			tareaDom(tarea);
		})
}

//Ver tareas en el modal
function verTarea(btn, tarea) {

	btn.addEventListener("click", function() {
		
		$('#editar_modal').show();
		var idTarea = document.getElementById("inputId");
		var titulo = document.getElementById("inputNombre2");
		var descripcion = document.getElementById("inputDescripcion2");

		idTarea.value = tarea.id_tarea;
		titulo.value = tarea.titulo;
		descripcion.value = tarea.descripcion;
		
		var editarForm = document.getElementById("anadirBorrar");
		
		var button = document.createElement("button");
		button.classList.add("btn", "btn-primary", "btn-lg", "btn-block");
		button.textContent = "Borrar Tarea";
		eliminarTarea(button);
		
		editarForm.appendChild(button);
	})
}

// Editar tareas
function editarTarea(event) {

	event.preventDefault();
	
	var ele = this;
	var lista = ele.parentNode;
	var h6 = lista.parentNode.childNodes[1];

	var idTarea = document.getElementById("inputId");

	var token = $("meta[name='_csrf']").attr("content");

	let titulo = $('#inputNombre2').val();
	if (titulo == null) {
		titulo = " ";
	}

	let descripcion = $('#inputDescripcion2').val();
	if (descripcion == null) {
		descripcion = " ";
	}

	fetch("/tarea/modificar/" + idTarea, {
		headers: { "Content-Type": "application/json; charset=utf-8", 'X-CSRF-TOKEN': token }, method: 'POST',
		credentials: 'same-origin',
		body: JSON.stringify({ titulo: titulo, descripcion: descripcion, prioridad: $('#inputPrioridad2').val(), estado: h6.textContent, empleado: $('#inputTrabajador2').val() })
	})
		.then(function(response) {
			if (response.ok) {
				return response.json();
			}
		})
		.then(tarea => {
			var tarea = this;
			
			tarea.parentNode;
			
			
		})
}

//Método para ver mis tareas
function misTareas(){
	
}

//Método para eliminar tareas
function eliminarTarea(btn, idTarea){

	btn.addEventListener("click", function(){
		
	});
}