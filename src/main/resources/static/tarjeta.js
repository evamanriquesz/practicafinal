const getTarjeta = async () => {
    let request = await fetch("/api/v1/tarjeta");
    if (request.status === 200) {
        const data = await request.json();
        document.getElementById("result").innerHTML = data.toString();
    }
};




async function guardarTarjeta(  numeroTarjeta, fechaCaducidad, cvv, usuarioId, gasto ){
    event.preventDefault();

    const dataObj={
        "numeroTarjeta":numeroTarjeta,
        "fechaCaducidad": fechaCaducidad,
        "cvv" : cvv,
        "usuarioId" : usuarioId,
        "gasto": gasto,
    };

    let res = await fetch("/api/v1/tarjetas",{
        method: 'POST',
        headers:{
            'Content-Type':'application/json',
        },
        body: JSON.stringify(dataObj)
    });

    if (res.ok){
    console.log(dataObj);
        alert("Todo ha ido bien :)");
        location.href='infoClientes.html';

    }else{
        alert("¡Vaya! Parece que algo ha ido mal en el form:(");
    }
}


/*
const form = document.getElementById('btnsubmit');

let tarjetaId;
let numeroTarjeta;
let fechaCaducidad;
let cvv;
let usuarioId;
let gasto;


form.addEventListener("click", function (event) {
	// stop form submission
	event.preventDefault();


	// validate the form

	    numeroTarjeta=document.getElementById('cardNumber').value;
        fechaCaducidad=document.getElementById('cardExpiry').value;
        cvv=document.getElementById('cardCvv').value;
        usuarioId=document.getElementById('usuarioId').value;
        gasto = document.getElementById('gasto').value;

        console.log(numeroTarjeta);
        console.log(gasto);


		alert("Se han recogido correctamente sus datos.");
	   //guardarJugador();



});

*/


function pagado(numeroTarjeta, fechaCaducidad, cvv, usuarioId, gasto)
{
     alert("Pagado :) Ya puedes realizar otra compra!");
   guardarTarjeta(numeroTarjeta, fechaCaducidad, cvv, usuarioId, gasto);
}


