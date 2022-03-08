const drag = (event) => {
	event.dataTransfer.setData("text/plain", event.target.id);
}

const allowDrop = (ev) => {
	ev.preventDefault();
	if (hasClass(ev.target, "dropzone")) {
		addClass(ev.target, "droppable");
	}
}

const clearDrop = (ev) => {
	removeClass(ev.target, "droppable");
}

const drop = (event) => {
	event.preventDefault();
	const data = event.dataTransfer.getData("text/plain");
	const element = document.querySelector(`#${data}`);
	try {
		// remove the spacer content from dropzone
		event.target.removeChild(event.target.firstChild);
		// add the draggable content
		event.target.appendChild(element);
		// remove the dropzone parent
		unwrap(event.target);
	} catch (error) {
		console.warn("can't move the item to the same place")
	}
	updateDropzones();
}

const updateDropzones = () => {
	/* after dropping, refresh the drop target areas
	  so there is a dropzone after each item
	  using jQuery here for simplicity */

	var dz = $('<div class="dropzone rounded" ondrop="drop(event)" ondragover="allowDrop(event)" ondragleave="clearDrop(event)"> &nbsp; </div>');

	// delete old dropzones
	$('.dropzone').remove();

	// insert new dropdzone after each item   
	dz.insertAfter('.card.draggable');

	// insert new dropzone in any empty swimlanes
	$(".items:not(:has(.card.draggable))").append(dz);
};

// helpers
function hasClass(target, className) {
	return new RegExp('(\\s|^)' + className + '(\\s|$)').test(target.className);
}

function addClass(ele, cls) {
	if (!hasClass(ele, cls)) ele.className += " " + cls;
}

function removeClass(ele, cls) {
	if (hasClass(ele, cls)) {
		var reg = new RegExp('(\\s|^)' + cls + '(\\s|$)');
		ele.className = ele.className.replace(reg, ' ');
	}
}

function unwrap(node) {
	node.replaceWith(...node.childNodes);
}

function despuesDeSoltar(event) {
	event.preventDefault();
	var ele = this;
	var lista = ele.parentNode;
	var h6 = lista.parentNode.childNodes[1];
	
	console.log(this.id + "  " + h6.textContent);
	return h6.textContent;

}

function anadirTarjeta() {

	let divCard = document.createElement("div");
	divCard.classList.add("card", "draggable", "shadow-sm");
	divCard.setAttribute("id", "tsk100");
	divCard.setAttribute("draggable", "true");
	divCard.setAttribute("ondragstart", "drag(event)");

	let divCardBody = document.createElement("div");
	divCardBody.classList.add("card-body", "p-2");


	let divCardTitle = document.createElement("div");
	divCardTitle.classList.add("card-title");

	let span = document.createElement("span");
	span.classList.add("badge", "rounded-pill", "bg-Warning");
	span.textContent = "Media";
	let link = document.createElement("link");
	link.classList.add("lead", "font-weight-light");
	link.textContent = "TSK-100";


	let para = document.createElement("p");
	para.textContent = "This is an item on the board. There is some descriptive text that explains the item here.";


	let button = document.createElement("button");
	button.classList.add("btn", "btn-primary", "btn-sm");
	button.textContent = "View";

	divCardTitle.appendChild(span);
	divCardTitle.appendChild(link);

	divCardBody.appendChild(divCardTitle);
	divCardBody.appendChild(para);
	divCardBody.appendChild(button);

	divCard.appendChild(divCardBody);

	let divDropZone = document.createElement("div");
	divDropZone.classList.add("dropzone", "rounded");
	divCard.setAttribute("ondrop", "drop(event)");
	divCard.setAttribute("ondragover", "allowDrop(event)");
	divCard.setAttribute("ondragleave", "clearDrop(event)");

	let columna = document.getElementsByClassName("columna")[0]
	columna.appendChild(divCard);
	columna.appendChild(divDropZone);

}

function cerrarModal() {

	let self = $(this);
	let ventana = self.closest(".modal");
	ventana.hide();
}



document.addEventListener('DOMContentLoaded', function() {

	$('.draggable').on('dragend', despuesDeSoltar);

})

