const form = document.getElementById("form");
const comprobar =document.getElementById("comprobar");
let contador=0;
export let contadorCorrectas=0;
document.getElementById('caja').style.display='none';

form.addEventListener("submit", function (event){
console.log("boton funciona");

event.preventDefault();
let fraseAMostrar =document.getElementById("Frase");

fetch("https://marvel-quote-api.p.rapidapi.com/", {
        "method": "GET",
        "headers": {
            "x-rapidapi-host": "marvel-quote-api.p.rapidapi.com",
            "x-rapidapi-key": "282dfec3f5mshdbe42c84ece2b8ap195853jsn1415907235f0"
        }
    })
    .then(response => {

    if(response.ok){
        console.log(response);
        return response.json();
        }else{throw response;}

    })

    .then(response =>{
        frase=response;
        vector=[];
        vector[0]=frase.Quote;
        vector[1]=frase.Speaker;
        vector[2]=frase.Title;
        console.log(frase);
        document.getElementById('Frase').innerHTML="&#34"+frase.Quote+"&#34";
        document.getElementById('Autor').innerHTML="-"+frase.Speaker;
        showHiddenInput();

    })

    .catch(err => {
        console.error(err);
    });

});

import{guardarJugador};

comprobar.addEventListener("submit", function (event){
event.preventDefault();

let fraseAMostrar =document.getElementById("Frase");

if(contador==4)
{
    location.href='finJuego.html';
    guardarJugador();
}
fetch("https://marvel-quote-api.p.rapidapi.com/", {
        "method": "GET",
        "headers": {
            "x-rapidapi-host": "marvel-quote-api.p.rapidapi.com",
            "x-rapidapi-key": "282dfec3f5mshdbe42c84ece2b8ap195853jsn1415907235f0"
        }
    })
    .then(response => {

    if(response.ok){
        console.log(response);
        return response.json();
        }else{throw response;}

    })


    .then(response =>{
        frase=response;
        vector=[];
        vector[0]=frase.Quote;
        vector[1]=frase.Speaker;
        vector[2]=frase.Title;
        console.log(frase);
        document.getElementById('Frase').innerHTML="&#34"+frase.Quote+"&#34";
        document.getElementById('Autor').innerHTML="-"+frase.Speaker;
    })

    .catch(err => {
        console.error(err);
    });

    console.log("boton comprobar");
    console.log(frase);

    let intento_pelicula = document.getElementById("Pelicula").value;
    if (frase.Title.toLowerCase().includes(intento_pelicula.toLowerCase()))
    {
        contadorCorrectas++;
        alert("respuesta correcta"+ contadorCorrectas+"/5");
        document.getElementById("Pelicula").value = "";
    }else{
        alert("respuesta incorrecta"+ contadorCorrectas+"/5")
        document.getElementById("Pelicula").value = "";
    }
        contador++;
});


function showHiddenInput(){
    document.getElementById('caja').style.display='block';
    document.getElementById('generar').style.display='none';
    document.getElementById('facil').style.display='none';

}


function hasValue(input, message) {
	if (input.value.trim() === "") {
		return showError(input, message);
	}
	return showSuccess(input);
}



//AQUI EL POST
async function guardarJugador(){
    event.preventDefault();

    const dataObj={
        "jugadorId":id,
        "aciertos":contadorCorrectas
    };

    let res = await fetch("/api/v1/jugador",{
        method: 'POST',
        headers:{
            'Content-Type':'application/json',
        },
        body: JSON.stringify(dataObj)
    });

    if (res.status == 201){
        alert("Todo ha ido bien :) Ya puedes iniciar sesión");
        location.href("resultados.html");
    }else{
        alert("¡Vaya! Parece que algo ha ido mal :(");
    }
}


function calcularID(){
    let url = "/api/v1/jugadores/"
}