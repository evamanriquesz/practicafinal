const form = document.getElementById("form");
const comprobar =document.getElementById("comprobar");
let contador=0;
let contadorCorrectas=0;
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
        document.getElementById('Pelicula').innerHTML="-"+frase.Title;
        showHiddenInput();

    })

    .catch(err => {
        console.error(err);
    });

});


comprobar.addEventListener("submit", function (event){
event.preventDefault();

if(contador==4)
{
    location.href='finJuego.html';
    modificarJugador();
}

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
    })


    .catch(err => {
        console.error(err);
    });


    console.log("boton comprobar");
    console.log(frase);

    let intento_autor = document.getElementById("Autor").value;
    let intento_pelicula = document.getElementById("Pelicula").value;
    if (frase.Speaker.toLowerCase().includes(intento_autor.toLowerCase()) && frase.Title.toLowerCase().includes(intento_pelicula.toLowerCase()))
    {
        contadorCorrectas++;
        alert("respuesta correcta"+ contadorCorrectas+"/5");
        document.getElementById("Autor").value = "";
        document.getElementById("Pelicula").value = "";
    }else if ( frase.Title.toLowerCase().includes(intento_pelicula.toLowerCase())){
          alert("titulo de la pelicula correcta"+ contadorCorrectas+"/5");
          document.getElementById("Autor").value = "";
          document.getElementById("Pelicula").value = "";
    }else if (frase.Speaker.toLowerCase().includes(intento_autor.toLowerCase())){
        alert("personaje correcto"+ contadorCorrectas+"/5");
        document.getElementById("Autor").value = "";
        document.getElementById("Pelicula").value = "";
    }else{
        alert("respuesta incorrecta")
        document.getElementById("Autor").value = "";
    }

contador++;




});


function showHiddenInput(){
    document.getElementById('caja').style.display='block';
    document.getElementById('generar').style.display='none';
    document.getElementById('dificil').style.display='none';

}


function hasValue(input, message) {
	if (input.value.trim() === "") {
		return showError(input, message);
	}
	return showSuccess(input);
}

function WriteFile()
{
   var fso  = CreateObject("Scripting.FileSystemObject");
   var fh = fso.CreateTextFile("/Resources/Fichero/Jugadores.txt", true);
   fh.WriteLine(miCadenaDeTexto);
   fh.Close();
}

//AQUI EL POST
async function modificarJugador(){
    event.preventDefault();

    calcularID();
    const dataObj={
        "jugadorId":id_max,
        "aciertos":contadorCorrectas
    };

    let res = await fetch("/api/v1/jugador/update/"+id_max,{
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

//funcion de otros compañeros
let id_max=-1;
function calcularID() {
    let url = "/api/v1/jugadores/"+localStorage.getItem('id');
    fetch(url)
    .then(response => response.json())
    .then(data => {

        id_max = data[0].jugador_id;
        for(let i=1;i<data.length;i++){
            if (data[i].jugador_id > id_max){
                id_max = data[i].jugador_id;
            }
        }
    })
}
