document.addEventListener("DOMContentLoaded", function() {

	cargarTareasArray();
	$("#anadirTarea").click(crearTarea);
	$("#editarTarea").click(editarTarea);
	$("#misTareas").click(misTareas);

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
				tarea2.nombreUsuario = tarea.nombreUsuario;
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
	divCard.setAttribute("name", tarea.estado);

	let divCardBody = document.createElement("div");
	divCardBody.classList.add("card-body", "p-2");

	let divCardTitle = document.createElement("div");
	divCardTitle.classList.add("card-title");

	let span = document.createElement("span");
	span.setAttribute("id", "prioridadTarea" + tarea.id_tarea);
	if (tarea.prioridad == "BAJA") {
		span.setAttribute("class", "badge rounded-pill bg-success");
	}
	else if (tarea.prioridad == "MEDIA") {
		span.setAttribute("class", "badge rounded-pill bg-warning");
	}
	else {
		span.setAttribute("class", "badge rounded-pill bg-danger");
	}
	span.textContent = tarea.prioridad;

	let link = document.createElement("a");
	link.classList.add("lead", "font-weight-light");
	link.textContent = "Tarea " + tarea.id_tarea;

	let link2 = document.createElement("a");
	link2.setAttribute("style", "text-align: right; float: right;");
	link2.setAttribute("id", "nombreUTarea" + tarea.id_tarea);
	link2.textContent = tarea.nombreUsuario;

	let titulo = document.createElement("p");
	titulo.setAttribute("id", "tituloTarea" + tarea.id_tarea);
	titulo.textContent = tarea.titulo;

	let button = document.createElement("button");
	button.classList.add("btn", "btn-primary", "btn-sm");
	button.textContent = "View";
	verTarea(button, tarea);

	divCardTitle.appendChild(span);
	divCardTitle.appendChild(link);
	divCardTitle.appendChild(link2);

	divCardBody.appendChild(divCardTitle);
	divCardBody.appendChild(titulo);
	divCardBody.appendChild(button);

	divCard.appendChild(divCardBody);

	let divDropZone = document.createElement("div");
	divDropZone.classList.add("dropzone", "rounded");
	divDropZone.setAttribute("ondrop", "drop(event)");
	divDropZone.setAttribute("ondragover", "allowDrop(event)");
	divDropZone.setAttribute("ondragleave", "clearDrop(event)");
	divDropZone.innerHTML = "&nbsp;";

	let columna = document.getElementById(tarea.estado);
	columna.appendChild(divCard);
	columna.appendChild(divDropZone);

	
}

//Insertar todas las tareas en el dom
function pintarTareas(todasTareas) {
	
	mantenerDropColumnas();
	borrarColumnas();

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
			else {
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

			$('#crear_modal').hide();

			tareaDom(tarea);
		})
}

// Método para cambiar la categoria
function cambiarCategoria(id, estado) {
	
	var newArray = id.split("a");
	var idTarea = newArray[2];
	
	var token = $("meta[name='_csrf']").attr("content");
	
	fetch("/tarea/modificar/estado/" + idTarea, {
		headers: { "Content-Type": "application/json; charset=utf-8", 'X-CSRF-TOKEN': token }, method: 'POST',
		credentials: 'same-origin',
		body: JSON.stringify({ estado: estado })
	})
		.then(function(response) {
			if (response.ok) {
				return response.json();
			}
			else {
				alert("ha ocurrido un error lo sentimos, pruebe más tarde o nunca");
			}
		})
		.then(tarea => {
			
			for (var i = 0; i < ArrayTareas.length; i++) {
				if (ArrayTareas[i].id_tarea == idTarea) {

					ArrayTareas[i].estado = tarea.estado;
					
					break;
				}
			}
		})
	
}
//Ver tareas en el modal
function verTarea(btn, tarea) {

	btn.addEventListener("click", function() {

		$('#editar_modal').show();
		var idTarea = document.getElementById("inputId");
		var titulo = document.getElementById("inputNombre2");
		var descripcion = document.getElementById("inputDescripcion2");

		var newArray = ArrayTareas.filter(tareas => tareas.id_tarea == tarea.id_tarea);

		idTarea.value = newArray[0].id_tarea;
		titulo.value = newArray[0].titulo;
		descripcion.value = newArray[0].descripcion;

		var editarForm = document.getElementById("anadirBorrar");
		var botonBorrar = document.getElementById("botonBorrar");

		if (botonBorrar != null) {
			eliminarTarea(botonBorrar, tarea.id_tarea);
		}
		else {
			var centro = document.createElement("center");
			var button = document.createElement("button");
			button.classList.add("btn", "btn-primary", "btn-lg", "btn-block", "mt-2");
			button.setAttribute("id", "botonBorrar");
			button.textContent = "Borrar Tarea";
			eliminarTarea(button, tarea.id_tarea);

			editarForm.appendChild(centro);
			centro.appendChild(button);
		}

	})
}

// Editar tareas
function editarTarea(event) {

	event.preventDefault();

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

	fetch("/tarea/modificar/" + idTarea.value, {
		headers: { "Content-Type": "application/json; charset=utf-8", 'X-CSRF-TOKEN': token }, method: 'POST',
		credentials: 'same-origin',
		body: JSON.stringify({ titulo: titulo, descripcion: descripcion, prioridad: $('#inputPrioridad2').val(), estado: "Preparada", empleado: $('#inputTrabajador2').val() })
	})
		.then(function(response) {
			if (response.ok) {
				return response.json();
			}
			else {
				alert("ha ocurrido un error lo sentimos");
			}
		})
		.then(tarea => {

			let titulo = document.getElementById("tituloTarea" + tarea.id_tarea);
			let nombreUsuario = document.getElementById("nombreUTarea" + tarea.id_tarea);
			let prioridad = document.getElementById("prioridadTarea" + tarea.id_tarea);

			titulo.textContent = tarea.titulo;
			nombreUsuario.textContent = tarea.nombreUsuario;
			if (tarea.prioridad == "BAJA") {
				prioridad.setAttribute("class", "badge rounded-pill bg-success");
			}
			else if (tarea.prioridad == "MEDIA") {
				prioridad.setAttribute("class", "badge rounded-pill bg-warning");
			}
			else {
				prioridad.setAttribute("class", "badge rounded-pill bg-danger");
			}
			prioridad.textContent = tarea.prioridad;

			for (var i = 0; i < ArrayTareas.length; i++) {
				if (ArrayTareas[i].id_tarea == idTarea) {

					ArrayTareas[i].titulo = tarea.titulo;
					ArrayTareas[i].nombreUsuario = tarea.nombreUsuario;
					ArrayTareas[i].descripcion = tarea.descripcion;
					ArrayTareas[i].prioridad = tarea.prioridad;

					break;
				}
			}

			$('#editar_modal').hide();
		})
}

//Método para ver mis tareas
function misTareas() {

	var token = $("meta[name='_csrf']").attr("content");

	fetch("/usuario/obtener", {
		headers: { "Content-Type": "application/json; charset=utf-8", 'X-CSRF-TOKEN': token }, method: 'GET', credentials: 'same-origin'
	})
		.then(res => res.json())
		.then(response => {

			var newArray = ArrayTareas.filter(tarea => tarea.nombreUsuario == response.nombreUsuario);

			var check = document.getElementById("misTareas");

			if (check.checked) {
				pintarTareas(newArray);
				console.log(newArray);
			}
			else {	
				pintarTareas(ArrayTareas);
			}
		})
}

//Método para borrar las columnas
function borrarColumnas() {
	
	let columna1 = document.getElementById("Preparada");
	let columna2 = document.getElementById("En Curso");
	let columna3 = document.getElementById("En revisión");
	let columna4 = document.getElementById("Finalizada");
	
	columna1.replaceChildren();
	columna2.replaceChildren();
	columna3.replaceChildren();
	columna4.replaceChildren();
	
	mantenerDropColumnas();
}

//Método para que el dropzone no se elimine de las columnas
// Funciona como le da la gana
function mantenerDropColumnas() {
	
	let DropZone1 = DropZonedemerde();
	let DropZone2 = DropZonedemerde();
	let DropZone3 = DropZonedemerde();
	let DropZone4 = DropZonedemerde();

	let columna1 = document.getElementById("Preparada");
	let columna2 = document.getElementById("En Curso");
	let columna3 = document.getElementById("En revisión");
	let columna4 = document.getElementById("Finalizada");
	
	columna1.appendChild(DropZone1);
	columna2.appendChild(DropZone2);
	columna3.appendChild(DropZone3);
	columna4.appendChild(DropZone4);
		
}

function DropZonedemerde(){
	
	let DropZone = document.createElement("div");
	DropZone.classList.add("dropzone", "rounded");
	DropZone.setAttribute("ondrop", "drop(event)");
	DropZone.setAttribute("ondragover", "allowDrop(event)");
	DropZone.setAttribute("ondragleave", "clearDrop(event)");
	DropZone.innerHTML = "&nbsp;";
	return DropZone;
}

//Método para eliminar tareas
function eliminarTarea(btn, idTarea) {

	btn.addEventListener("click", function() {

		var token = $("meta[name='_csrf']").attr("content");

		fetch("/tarea/borrar/" + idTarea, {
			headers: { "Content-Type": "application/json; charset=utf-8", 'X-CSRF-TOKEN': token }, method: 'DELETE', credentials: 'same-origin'
		})
			.then(function(response) {

				if (response.ok) {
					return response.json();
				}
				else {
					alert("Lo sentimos ha habido un error, pruebe mas tarde");
				}
			})
			.then(response => {

				for (var i = 0; i < ArrayTareas.length; i++) {
					if (ArrayTareas[i].id_tarea == idTarea) {
						ArrayTareas.splice(i, 1);
						break;
					}
				}

				$('#editar_modal').hide();
				pintarTareas(ArrayTareas);

			})
	});
}