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
const TELEFONO_REQUIRED = "Por favor introduzca su telefono";


form.addEventListener("submit", function (event) {
	// stop form submission
	event.preventDefault();

	// validate the form
	let nameValid = hasValue(form.elements["fname"], NAME_REQUIRED);
	let surnameValid = hasValue(form.elements["lname"], APELLIDOS_REQUIRED);
	let emailValid= hasValue(form.elements["email"], EMAIL_REQUIRED);
	let telfValid = hasValue(form.elements["phone"], TELEFONO_REQUIRED);


	// if valid, submit the form.
	if (nameValid && surnameValid && emailValid && telfValid) {
		alert("Se han recogido correctamente sus datos."+"\nLa información recogida es: " +"\nNombre: "+form.elements["fname"].value + "\nApellidos: " + form.elements["lname"].value+ "\nEmail: "+ form.elements["email"].value + "\nTelefono: "+ form.elements["phone"].value+"\n ¡Suerte!");
	}
});
