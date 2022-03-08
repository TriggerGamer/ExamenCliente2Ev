function anadirFilaTabla(responseTarea){
    let resultados = document.getElementById("resultados");

    let tr = document.createElement('tr');
    
    let td1 = document.createElement('td');
    let td2 = document.createElement('td');
    let span = document.createElement('span');
    let td3 = document.createElement('td');
    let td4 = document.createElement('td');
    let a = document.createElement('a');
    
	td1.textContent = responseTarea.titulo;
	
	if(responseTarea.prioridad == "BAJA"){
		span.setAttribute("class", "badge bg-primary");
		span.textContent = responseTarea.prioridad;
	}else if(responseTarea.prioridad == "MEDIA"){
		span.setAttribute("class", "badge bg-warning");
		span.textContent = responseTarea.prioridad;
	}
	else{
		span.setAttribute("class", "badge bg-danger");
		span.textContent = responseTarea.prioridad;
	}
    
    td3.textContent = responseTarea.estado;
    
    a.setAttribute("type", "button");
    a.setAttribute("class", "btn btn-success");
    a.setAttribute("href", "/tarea/" + responseTarea.id_tarea);
 	a.textContent = "Ver Tarea"  

    resultados.appendChild(tr);
    tr.appendChild(td1);
    tr.appendChild(td2);
    td2.appendChild(span);
    tr.appendChild(td3);
    tr.appendChild(td4);
    td4.appendChild(a);
}

function crearTarea(event){
	
	event.preventDefault();
		
		let enlace = document.getElementById("formTareas").action;

		var csrfToken = $("[name='_csrf']").attr("value");
		
		let titulo = $('#inputNombre').val();
		if(titulo == null){
			titulo = " ";
		}
		
		let descripcion = $('#inputDescripcion').val();
		if(descripcion == null){
			descripcion = " ";
		}

		fetch(enlace, {
			headers: { "Content-Type": "application/json; charset=utf-8", 'X-CSRF-TOKEN': csrfToken }, method: 'POST',
			credentials: 'same-origin',
			body: JSON.stringify({ titulo: titulo, descripcion: descripcion, prioridad:  $('#inputPrioridad').val(), estado: "Preparada", empleado:  $('#inputTrabajador').val() })
		})
			.then(function(response) {
				if (response.ok) {
					return response.json();
				}
			})
			.then(response => {
				anadirFilaTabla(response);
			})
}

document.addEventListener("DOMContentLoaded", function () {

   $("#anadirTarea").click(crearTarea);
   
});

