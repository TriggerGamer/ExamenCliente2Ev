function anadirFilaTabla(nombre,email){
    let resultados = document.getElementById("resultados");

    let li = document.createElement('li');
    li.classList.add("list-group-item", "d-flex", "justify-content-between", "align-items-center");
    li.textContent = nombre;
    li.addEventListener("click",mostrarUsuario);
    let span = document.createElement('span')
    span.classList.add("badge", "bg-primary", "rounded-pill");
    span.textContent=email;
    li.appendChild(span);
    resultados.appendChild(li);
}

function limpiarTabla() {
    let resultados = document.getElementById("resultados");
    resultados.replaceChildren();
}
function obtenerUsuarios() {

    let resultados = document.getElementById("resultados");
    resultados.replaceChildren();

    fetch('/todos', {headers: {"Content-Type": "application/json; charset=utf-8"}})
        .then(res => res.json()) // parse response as JSON (can be res.text() for plain response)
        .then(response => {

            for (let usuario of response) {
                anadirFilaTabla(usuario.nombre, usuario.email);
            }
        })
}

function mostrarUsuario(self){
    $("#modal").modal("show");
    var email = this.firstElementChild.innerText;
    fetch('/usuario/'+email, {headers: {"Content-Type": "application/json; charset=utf-8"}})
        .then(res => res.json()) // parse response as JSON (can be res.text() for plain response)
        .then(data => {

            let modal = document.getElementById("modal-body");
            modal.replaceChildren();
            let p1 = document.createElement('p');
            p1.innerText=data.nombre;
            let p2 = document.createElement('p');
            p2.innerText=data.email;

            modal.appendChild(p1);
            modal.appendChild(p2);
        })
}

function creaUsuario() {
    let errorDiv = document.getElementById("errorDiv");
    errorDiv.replaceChildren();
    if ($('#inputNombre').val() != "" && $('#inputEmail').val() != "") {

        fetch('/crear2', {
            headers: {
                'Content-type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify({nombre: $('#inputNombre').val(), email: $('#inputEmail').val()})
        })
            .then(function(response) {
                if(response.ok) {
                    return response.json()
                } else {
                    throw "El email ya existe";
                }

            })
            .then(data => {
                anadirFilaTabla(data.nombre,data.email);
            })
            .catch(function(messsageDeError) {
                let errorDiv = document.getElementById("errorDiv");
                let div = document.createElement('div');
                div.classList.add("alert", "alert-dismissible", "alert-danger");
                let button = document.createElement('button');
                button.classList.add("btn-close");
                button.setAttribute("data-bs-dismiss","alert");
                let link = document.createElement('a');
                link.classList.add("alert-link");
                link.setAttribute("href","#");
                var linkText = document.createTextNode(messsageDeError);
                link.appendChild(linkText);
                div.appendChild(button);
                div.appendChild(link);
                var textNode = document.createTextNode(" Prueba con otro");
                div.appendChild(textNode);
                errorDiv.appendChild(div);
        });
    }
}

document.addEventListener("DOMContentLoaded", function () {

    $("#registrar").click(creaUsuario);

    $("#refrescar").click(obtenerUsuarios);

    $("#limpiar").click(limpiarTabla);
});

