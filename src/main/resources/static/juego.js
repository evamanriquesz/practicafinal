// show a message with a type of the input
function showMessage(input, message, type) {
	// get the small element and set the message
	const msg = input.parentNode.querySelector("small");
	msg.innerText = message;
	// update the class for the input
	input.className = type ? "success" : "error";
	return type;
}

function showError(input, message) {
	return showMessage(input, message, false);
}

function showSuccess(input) {
	return showMessage(input, "", true);
}

function hasValue(input, message) {
	if (input.value.trim() === "") {
		return showError(input, message);
	}
	return showSuccess(input);
}

function validateEmail(input, requiredMsg, invalidMsg) {
	// check if the value is not empty
	if (!hasValue(input, requiredMsg)) {
		return false;
	}
	// validate email format
	const emailRegex =
		/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

	const email = input.value.trim();
	if (!emailRegex.test(email)) {
		return showError(input, invalidMsg);
	}
	return true;
}

const form = document.getElementById('btnsubmit');

const JUGADOR_ID_REQUIRED = "Por favor introduzca su ID de jugador";
const NAME_REQUIRED = "Por favor introduzca su nombre";
const APELLIDOS_REQUIRED = "Por favor introduzca su apellido";
const EMAIL_REQUIRED = "Por favor introduzca su email";
const EDAD_REQUIRED = "Por favor introduzca su edad"
const GENERO_REQUIRED = "Por favor introduzca su genero"
const NIVEL_REQUIRED = "Por favor introduzca el nivel";


let jugadorId;
let nombre;
let apellidos;
let edad;
let email;
let tipoGenero;
let selected;


form.addEventListener("click", function (event) {
	// stop form submission
	event.preventDefault();
	//obtengo el valor del genero
    GetCheckedVal();
    ShowSelected();

    console.log(tipoGenero);
    console.log(selected);
	// validate the form
	let jugadorIdValid = hasValue(document.getElementById('jugadorId'), JUGADOR_ID_REQUIRED);
	let nombreValid = hasValue(document.getElementById('nombre'), NAME_REQUIRED);
	let apellidosValid = hasValue(document.getElementById('apellidos'), APELLIDOS_REQUIRED);
	let emailValid= hasValue(document.getElementById('email'), EMAIL_REQUIRED);
	let edadValid = hasValue(document.getElementById('edad'), EDAD_REQUIRED);


	// if valid, submit the form.
	if (jugadorIdValid && nombreValid && apellidosValid && emailValid && edadValid) {
	    jugadorId = document.getElementById('jugadorId').value;
	    nombre=document.getElementById('nombre').value;
        apellidos=document.getElementById('apellidos').value;
        edad=document.getElementById('edad').value;
        email=document.getElementById('email').value;
        aciertos = 1 ;

            console.log("los valid");
            console.log(jugadorIdValid);
            console.log(nombreValid);
            console.log(apellidosValid);
            console.log(emailValid);
            console.log(edadValid);


		alert("Se han recogido correctamente sus datos."+"\nLa información recogida es: " +"\nID jugador: "+jugadorId +"\nNombre: "+nombre + "\nApellidos: " + apellidos+ "\nEmail: "+ email + "\nEdad: "+ edad+ "\nGenero: "+ tipoGenero+ "\nNivel: "+ selected);
	   //guardarJugador();
	     console.log(aciertos);
	    guardarJugador(jugadorId, nombre, apellidos, edad, tipoGenero, email, selected, aciertos);

	    if (selected.includes('Facil')){
            location.href='Facil.html';
        }else if (selected.includes('Medio')){
            location.href='medio.html';
        }else{
            location.href='dificil.html';
        }
	}

});

function GetCheckedVal() {
    let hombre = document.getElementById('hombre').checked;
    if(hombre){
        tipoGenero="Hombre"
    }

    let mujer = document.getElementById('mujer').checked;
    if(mujer){
      tipoGenero="Mujer"
    }

    let otro = document.getElementById('otro').checked;
    if(otro){
        tipoGenero="Otro"
    }
}

function ShowSelected()
{
    /* Para obtener el valor */
    var cod = document.getElementById("nivel").value;


    /* Para obtener el texto */
    var combo = document.getElementById("nivel");
     selected = combo.options[combo.selectedIndex].text;
}




//AQUI EL POST
async function guardarJugador(jugadorId, nombre, apellidos, edad, tipoGenero, email, selected, aciertos){
    event.preventDefault();

    const dataObj={
        "jugadorId" : jugadorId,
        "nombre":nombre,
        "apellidos": apellidos,
        "edad" : edad,
        "genero" : tipoGenero,
        "email": email,
        "nivel" : selected,
        "aciertos" : 1,
    };

    let res = await fetch("/api/v1/jugadores",{
        method: 'POST',
        headers:{
            'Content-Type':'application/json',
        },
        body: JSON.stringify(dataObj)
    });

    if (res.status == 201){ //aqui antes pponia 201
        alert("Todo ha ido bien :) Ya puedes jugar!");
        //location.href("resultados.html");
    }else{
        alert("¡Vaya! Parece que algo ha ido mal en el form:(");
    }
}
