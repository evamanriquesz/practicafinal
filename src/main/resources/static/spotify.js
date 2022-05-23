
///////////////////////////////////////////////////////////////////////////////////
//ESTO ES PARA EL ONLOAD
//////////////////////////////////////////////////////////////////////////////////

function lanzadera()
{
    mostrarPlaylists();
    showArtistas();
}


///////////////////////////////////////////////////////////////////////////////////
//ESTO ES PARA LA PLAYLIST PARA CONECTAR CON LA API DE SPOTIFY (AQUI HAY SEGURIDAD)
//////////////////////////////////////////////////////////////////////////////////

var client_id = '263978e85f454d5eb0aa62463c8e27dd';
var client_secret = '32e088a57d6b4137865a70f6b0724e57';
var tokenIntermedio = "";

const mostrarPlaylists = async () => {
// Call the API
// This is a POST request, because we need the API to generate a new token for us
fetch('https://accounts.spotify.com/api/token', {
	method: 'POST',
	body: 'grant_type=client_credentials&client_id=' + client_id + '&client_secret=' + client_secret,
	headers: {
		'Content-Type': 'application/x-www-form-urlencoded'
	}
}).then(function (resp) {

	// Return the response as JSON
	return resp.json();

}).then(function (data) {

        var token = data.access_token;

        // Call the API
        // This is a POST request, because we need the API to generate a new token for us
        fetch('https://api.spotify.com/v1/playlists/71QiIRIZPacpZBCzRbOLp5?si=6de18c86e16c4d58/tracks', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer '+ token
            }
        }).then(function (resp) {

            // Return the response as JSON
            return resp.json();

        }).then(function (data) {

            // Log the API data
            console.log('data', data);

            //todo lo de mostrar habria que hacerlo aquí o al menos llamar a una función

                const info_playlists_box = document.getElementById("infoPlaylists"); //zona para la tabla
                let table = document.createElement('table');
                let thead = document.createElement('thead');
                let tbody = document.createElement('tbody');

                table.appendChild(thead);
                table.appendChild(tbody);

                info_playlists_box.appendChild(table);

                let row_1 = document.createElement('tr');

                let heading_1 = document.createElement('th');
                heading_1.innerHTML="Número  ";
                let heading_2 = document.createElement('th');
                heading_2.insertAdjacentText("beforeend","Nombre");
                let heading_3 = document.createElement('th');
                heading_3.insertAdjacentText("beforeend","Álbum");
                let heading_4 = document.createElement('th');
                heading_4.insertAdjacentText("beforeend","Reproducir");

                 row_1.appendChild(heading_1);
                 row_1.appendChild(heading_2);
                 row_1.appendChild(heading_3);
                 row_1.appendChild(heading_4);

                 thead.appendChild(row_1);

            let max_tracks = data.tracks.limit;
               // console.log(data.tracks.limit);
                //console.log(data.tracks.items[99].track.name);

            for (let i = 0; i<max_tracks; i++)
            {
                console.log(data.tracks.items[i].track.name);

                let rows_data = document.createElement('tr');

                let heading_1 = document.createElement('td');
                heading_1.innerHTML=i+1;
                let heading_2 = document.createElement('td');
                heading_2.insertAdjacentText("beforeend",data.tracks.items[i].track.name);
                let heading_3 = document.createElement('td');
                heading_3.insertAdjacentText("beforeend",data.tracks.items[i].track.album.name);
                let heading_4 = document.createElement('td');
                let etiqueta_a = document.createElement('a');
                etiqueta_a.setAttribute("href", data.tracks.items[i].track.external_urls.spotify);
                //console.log(data.tracks.items[i].track.external_urls.spotify) ;
                let texto_etiqueta_a = document.createTextNode("Dale al play");
                etiqueta_a.appendChild(texto_etiqueta_a);
                heading_4.appendChild(etiqueta_a);


                rows_data.appendChild(heading_1);
                rows_data.appendChild(heading_2);
                rows_data.appendChild(heading_3);
                rows_data.appendChild(heading_4);

                tbody.appendChild(rows_data);

            }

        }).catch(function (err) {

            // Log any errors
            console.log('something went wrong', err);

        });

}).catch(function (err) {

	// Log any errors
	console.log('something went wrong', err);

});
};


///////////////////////////////////////////////////////////////////////////////////
//ESTO ES PARA LO DE LOS ARTISTAS, CONECTANDO CON LA BBDD
//////////////////////////////////////////////////////////////////////////////////

let div;

 function anadirFavorito(artistaId){
console.log("añadir " + artistaId);
anadir(artistaId);
//show();
}


async function anadir(artistaId){
    //Evito que recargue la página
    event.preventDefault();

     let res = await fetch("/api/v1/artistas",{
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
        }});

        console.log(res);

        if (res.status == 200){
            const data = await res.json();
            //console.log(data); //ok
            //console.log(data[0]); //ok
             for (let i=0; i<data.length; i++)
             {
                 let artista1 = data[i];
                 let artistaId1 = artista1["artistaId"];
                 let nombreArtista1 = artista1["nombreArtista"];
                 let favoritos1 = artista1["favoritos"];

                 console.log("artistaId: " + artistaId);
                 console.log("artistaId1: " + artistaId1);



                 if (artistaId == artistaId1)
                 {
                    console.log("este es el bueno: "+ artistaId1); //ok
                    let nuevosFavoritos = favoritos1+1;
                    const dataObj = {
                            "artistaId" : artistaId1,
                            "nombreArtista" : nombreArtista1,
                            "favoritos" : nuevosFavoritos
                        };

                        let res1 = await fetch("/api/v1/artistas/"+artistaId1,{
                            method: 'PUT',
                            headers: {
                                'Content-Type': 'application/json',
                            },
                            body: JSON.stringify(dataObj)
                        });
                        console.log("nuevos favoritos "+ nuevosFavoritos);
                        showArtistas();

                 }

             }
        }

}



function showArtistas(){
    console.log("hola");
    show();
}

    async function show(){
        //Evito que recargue la página
        //event.preventDefault();

        let res = await fetch("/api/v1/artistas",{
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
        }});

        //console.log(res);

        if (res.status == 200){
            const data = await res.json();
            //console.log(data); //ok
            //console.log(data[0]); //ok
            div = document.getElementById("mostrar");


             let contenidoHTML='';
             for (let i=0; i<data.length; i++)
             {
             let artista1 = data[i];
             let artistaId1 = artista1["artistaId"];
             let nombreArtista1 = artista1["nombreArtista"];
             let favoritos1 = artista1["favoritos"];

             contenidoHTML = contenidoHTML + '<div class ="col-3"> <div class="card" id="ident' + i+1 + ' style="width:18 rem;" ><div class="card-body"><h4 class="card-title"> ' + nombreArtista1 + '</h4><p class="card-text">Favoritos: ' + favoritos1 + '</p><button type="button" class="btn btn-outline-danger" onclick ="anadirFavorito('+artistaId1+')">Añadir Favorito</button> <button type="button" class="btn btn-outline-danger" data-toggle="modal" data-target="#exampleModal" onclick ="borrarArtista('+artistaId1+')">Eliminar</button> <button type="button" class="btn btn-outline-danger" data-toggle="modal" data-target="#exampleModal" onclick="showAmbas('+artistaId1+')" >VerCanciones</button> </div></div></div>';


             console.log("artista " + artistaId1); //ok
             console.log("favoritos: " + favoritos1);
        }
        div.innerHTML = contenidoHTML;

    }

}


///////////////////////////////////////////////////////////////////////////////////
//ESTO ES PARA MOSTRAR ARTISTAS CON SUS CANCIONES Y SUS FAVORITOS
//////////////////////////////////////////////////////////////////////////////////

function showArtistaCancion()
{
    console.log("se esta ejecutando show Artista Cancion");
    showAC();
}

async function showAC()
{

     let res = await fetch("/api/v1/artistaCancionAll",{
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json',
            }});

            //console.log(res);

            if (res.status == 200){
                const data = await res.json();
                //console.log(data);

                const cancionArtistaAll_box = document.getElementById("mostrarArtistaCancion");
                let table = document.createElement('table');
                let thead = document.createElement('thead');
                let tbody = document.createElement('tbody');

                table.appendChild(thead);
                table.appendChild(tbody);

                cancionArtistaAll_box.appendChild(table);

                let row_1 = document.createElement('tr');

                let heading_1 = document.createElement('th');
                heading_1.innerHTML="Canción";
                let heading_2 = document.createElement('th');
                heading_2.insertAdjacentText("beforeend","Artista");
                let heading_3 = document.createElement('th');
                heading_3.insertAdjacentText("beforeend","Álbum");
                let heading_4 = document.createElement('th');
                heading_4.insertAdjacentText("beforeend","Favoritos del artista");

                 row_1.appendChild(heading_1);
                 row_1.appendChild(heading_2);
                 row_1.appendChild(heading_3);
                 row_1.appendChild(heading_4);

                 thead.appendChild(row_1);

            //let max_tracks = data.tracks.limit;
               // console.log(data.tracks.limit);
                //console.log(data.tracks.items[99].track.name);

            for (let i = 0; i<data.length; i++)
            {

                let rows_data = document.createElement('tr');

                let heading_1 = document.createElement('td');
                heading_1.innerHTML=data[i].nombreCancion;
                let heading_2 = document.createElement('td');
                heading_2.insertAdjacentText("beforeend",data[i].nombreArtista);
                let heading_3 = document.createElement('td');
                heading_3.insertAdjacentText("beforeend",data[i].album);
                let heading_4 = document.createElement('td');
                heading_4.insertAdjacentText("beforeend", data[i].favoritos)


                rows_data.appendChild(heading_1);
                rows_data.appendChild(heading_2);
                rows_data.appendChild(heading_3);
                rows_data.appendChild(heading_4);

                tbody.appendChild(rows_data);

            }

            }
}

///////////////////////////////////////////////////////////////////////////////////////////////
//ESTO ES PARA OCULTAR ARTISTAS CON SUS CANCIONES Y SUS FAVORITOS, para luego mostrar los nuevos
//////////////////////////////////////////////////////////////////////////////////////////////
//creo q no se usa
function hideArtistaCancion()
{
    console.log("se esta ejecutando hide Artista Cancion");
    hideAC();
}

async function hideAC()
{
    document.getElementById("mostrarArtistaCancion").style.visibility = "hidden";
}

///////////////////////////////////////////////////////////////////////////////////////////////
//ESTO ES PARA VER CANCIONES DE CADA ARTISTA EN UN ALERT
//////////////////////////////////////////////////////////////////////////////////////////////


function verCancionesDelArtista(artistaId)
{
    //location.href="#CancionesArtista";
    console.log("se esta ejecutando show canciones by artista");
    verCDA(artistaId);
}

async function verCDA(artistaId)
{
     event.preventDefault();

     let res = await fetch("/api/v1/artistaCancionAll/" +artistaId,{
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json',
                        'Accept': 'application/json',
                }});

                //console.log(res);

                if (res.status == 200){
                    const data = await res.json();
                    //console.log(data);
                    let divModal = document.getElementById("mostrarModal");

                    console.log("data", data);

                    let canciones = [];

                     for (let i=0; i<data.length; i++)
                     {
                     let artista1 = data[i];
                     let cancionId1 = artista1["cancionId"];
                     let nombreCancion1 = artista1["nombreCancion"];
                     let artistaId1 = artista1["artistaId"];
                     let nombreArtista1 = artista1["nombreArtista"];
                     let album1 = artista1["album"];
                     let favoritos1 = artista1["favoritos"];

                     canciones.push(nombreCancion1);
                     }

                        console.log("canciones: " + canciones);
                        alert("Las canciondes del artista son: \n" + canciones);


                   }
}



///////////////////////////////////////////////////////////////////////////////////
//ESTO ES PARA VER LAS CANCIONES DE CADA ARTISTA EN TABLA
//////////////////////////////////////////////////////////////////////////////////

function showCancionesDelArtistaTabla(artistaId)
{
    console.log("se esta ejecutando show Artista Cancion tabla");
    showCDAT(artistaId);
}

async function showCDAT(artistaId)
{

     let res = await fetch("/api/v1/artistaCancionAll/" + artistaId,{
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json',
            }});

            //console.log(res);

            if (res.status == 200){
                const data = await res.json();
                //console.log(data);

                const cancionArtistaAll_box = document.getElementById("infoCadaArtista");
                let table = document.createElement('table');
                let thead = document.createElement('thead');
                let tbody = document.createElement('tbody');

                table.appendChild(thead);
                table.appendChild(tbody);

                cancionArtistaAll_box.appendChild(table);

                let row_1 = document.createElement('tr');

                let heading_1 = document.createElement('th');
                heading_1.innerHTML="Canción";
                let heading_2 = document.createElement('th');
                heading_2.insertAdjacentText("beforeend","Artista");
                let heading_3 = document.createElement('th');
                heading_3.insertAdjacentText("beforeend","Álbum");
                let heading_4 = document.createElement('th');
                heading_4.insertAdjacentText("beforeend","Favoritos del artista");

                 row_1.appendChild(heading_1);
                 row_1.appendChild(heading_2);
                 row_1.appendChild(heading_3);
                 row_1.appendChild(heading_4);

                 thead.appendChild(row_1);

            //let max_tracks = data.tracks.limit;
               // console.log(data.tracks.limit);
                //console.log(data.tracks.items[99].track.name);

            for (let i = 0; i<data.length; i++)
            {

                let rows_data = document.createElement('tr');

                let heading_1 = document.createElement('td');
                heading_1.innerHTML=data[i].nombreCancion;
                let heading_2 = document.createElement('td');
                heading_2.insertAdjacentText("beforeend",data[i].nombreArtista);
                let heading_3 = document.createElement('td');
                heading_3.insertAdjacentText("beforeend",data[i].album);
                let heading_4 = document.createElement('td');
                heading_4.insertAdjacentText("beforeend", data[i].favoritos)


                rows_data.appendChild(heading_1);
                rows_data.appendChild(heading_2);
                rows_data.appendChild(heading_3);
                rows_data.appendChild(heading_4);

                tbody.appendChild(rows_data);

            }

            }
}

/////////////////////////////////////////////////////////////////////////////////////////////
//ESTO ES PARA HACER AMBAS OPCIONES A LA VEZ
//////////////////////////////////////////////////////////////////////////////////////////////

function showAmbas(artistaId)
{
   verCancionesDelArtista(artistaId);
   showCancionesDelArtistaTabla(artistaId);
}


///////////////////////////////////////////////////////////////////////////////////////////////
//ESTO ES PARA OCULTAR ARTISTAS CON SUS CANCIONES Y SUS FAVORITOS, para luego mostrar los nuevos
//////////////////////////////////////////////////////////////////////////////////////////////
//creo q no se usa
function hideArtistaCancion()
{
    console.log("se esta ejecutando hide Artista Cancion");
    hideAC();
}

async function hideAC()
{
    document.getElementById("mostrarArtistaCancion").style.visibility = "hidden";
}





///////////////////////////////////////////////////////////////////////////////////////////////
//ESTO ES PARA BORRAR ARTISTAS
//////////////////////////////////////////////////////////////////////////////////////////////

function borrarArtista(artistaId)
{
    console.log('Se esta ejecutando borrar Artista');
    borrar(artistaId);
}

async function borrar(artistaId)
{
 //   event.preventDefault();


    let res = await fetch("/api/v1/artistas",
    {
        method: 'GET',
        headers: {
            'Content-Type':'application/json',
        }});

        console.log(res.status);

         if (res.status == 200){
            const data = await res.json();
            //console.log(data); //ok
            //console.log(data[0]); //ok

            console.log('estamos dentro');


             for (let i=0; i<data.length; i++)
             {
                let artista1 = data[i];
                let artistaId1 = artista1["artistaId"];

                 console.log("artistaId: " + artistaId);
                 console.log("artistaId1: " + artistaId1);

                 if (artistaId == artistaId1)
                 {
                    console.log("este es el bueno: "+ artistaId1); //ok


                        let res2 = await fetch("/api/v1/artistas/"+artistaId1,{
                            method: 'DELETE',
                        });
                    showArtistas();

                 }
             }

         }

}






