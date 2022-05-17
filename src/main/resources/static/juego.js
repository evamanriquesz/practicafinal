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

const form = document.querySelector("#signup");

const NAME_REQUIRED = "Por favor introduzca su nombre";
const APELLIDOS_REQUIRED = "Por favor introduzca su apellido";
const EMAIL_REQUIRED = "Por favor introduzca su email";
const EDAD_REQUIRED = "Por favor introduzca su edad"
const GENERO_REQUIRED = "Por favor introduzca su genero"
const NIVEL_REQUIRED = "Por favor introduzca el nivel";



let nombre;
let apellidos;
let edad;
let email;
let tipoGenero;
let selected;

form.addEventListener("submit", function (event) {
	// stop form submission
	event.preventDefault();
    GetCheckedVal();
    ShowSelected();
	// validate the form
	let nombreValid = hasValue(form.elements["nombre"], NAME_REQUIRED);
	let apellidoValid = hasValue(form.elements["apellidos"], APELLIDOS_REQUIRED);
	let emailValid= hasValue(form.elements["email"], EMAIL_REQUIRED);
	let edadValid = hasValue(form.elements["edad"], EDAD_REQUIRED);



	// if valid, submit the form.
	if (nombreValid && apellidoValid && emailValid && edadValid) {
	nombre=form.elements["nombre"].value;
        apellidos=form.elements["apellidos"].value;
        email=form.elements["email"].value;
        edad=form.elements["edad"].value;
		alert("Se han recogido correctamente sus datos."+"\nLa información recogida es: " +"\nNombre: "+nombre + "\nApellidos: " + apellidos+ "\nEmail: "+ email + "\nEdad: "+ edad+ "\nGenero: "+ tipoGenero+ "\nNivel: "+ selected);
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

