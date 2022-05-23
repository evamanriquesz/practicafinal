const getTarjeta = async () => {
    let request = await fetch("/api/v1/tarjeta");
    if (request.status === 200) {
        const data = await request.json();
        document.getElementById("result").innerHTML = data.toString();
    }
};

/*
function pagado()
{
     alert("Pagado :) Ya puedes realizar otra compra!");

    location.href='fanstore.html';
}
*/


async function guardarTarjeta( tarjetaId, numeroTarjeta, fechaCaducidad, cvv, usuarioId, gasto ){
    event.preventDefault();

    const dataObj={
        "tarjetaId" : id_max,
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
        alert("Todo ha ido bien :)");
    }else{
        alert("¡Vaya! Parece que algo ha ido mal en el form:(");
    }
}


function calcularID() {
    let url = "/api/v1/tarjetas/";
    fetch(url)
    .then(response => response.json())
    .then(data => {
        console.log(data);
        id_max = data[0].tarjetaId;
        for(let i=1;i<data.length;i++){
            if (data[i].tarjetaId > id_max){
                id_max = data[i].tarjetaId;
            }
        }

        id_max=id_max+1;
    })
}

document.addEventListener('DOMContentLoaded',calcularID());

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




		alert("Se han recogido correctamente sus datos.");
	   //guardarJugador();
	       localStorage.setItem('tarjetaId', id_max)

	    guardarTarjeta(tarjetaId,numeroTarjeta, fechaCaducidad, cvv, usuarioId, gasto);
	    alert("Pagado :) Ya puedes realizar otra compra!");
	    location.href='fanstore.html';


});



