async function mostrarResultados(){

    event.preventDefault();

     let resp = await fetch("/api/v1/jugadores",{
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
        }})

                .then(function (resp) {

                           // Return the response as JSON
                           return resp.json();

                       })

               .then(function (data) {

                           // Log the API data
                           console.log('data', data);

                           //todo lo de mostrar habria que hacerlo aquí o al menos llamar a una función

                               const info_jugadores_box = document.getElementById("mostrarResultados"); //zona para la tabla
                               let table = document.createElement('table');
                               let thead = document.createElement('thead');
                               let tbody = document.createElement('tbody');

                               table.appendChild(thead);
                               table.appendChild(tbody);

                               info_jugadores_box.appendChild(table);

                               let row_1 = document.createElement('tr');

                               let heading_1 = document.createElement('th');
                               heading_1.innerHTML="ID";
                               let heading_2 = document.createElement('th');
                               heading_2.insertAdjacentText("beforeend","Nombre");
                               let heading_3 = document.createElement('th');
                               heading_3.insertAdjacentText("beforeend","Apellidos");
                               let heading_4 = document.createElement('th');
                               heading_4.insertAdjacentText("beforeend","Edad");
                               let heading_5 = document.createElement('th');
                              heading_5.insertAdjacentText("beforeend","Email");
                              let heading_6 = document.createElement('th');
                             heading_6.insertAdjacentText("beforeend","Genero");
                             let heading_7 = document.createElement('th');
                            heading_7.insertAdjacentText("beforeend","Nivel");
                            let heading_8 = document.createElement('th');
                           heading_8.insertAdjacentText("beforeend","Aciertos");


                                row_1.appendChild(heading_1);
                                row_1.appendChild(heading_2);
                                row_1.appendChild(heading_3);
                                row_1.appendChild(heading_4);
                                row_1.appendChild(heading_5);
                                row_1.appendChild(heading_6);
                                row_1.appendChild(heading_7);
                                row_1.appendChild(heading_8);

                                thead.appendChild(row_1);


                              // console.log(data.tracks.limit);
                               //console.log(data.tracks.items[99].track.name);

                           for (let i = 0; i<data.length; i++)
                           {


                               let rows_data = document.createElement('tr');

                               let heading_1 = document.createElement('td');
                               heading_1.insertAdjacentText("beforeend",data[i].jugadorId);
                               let heading_2 = document.createElement('td');
                               heading_2.insertAdjacentText("beforeend",data[i].nombre);
                               let heading_3 = document.createElement('td');
                               heading_3.insertAdjacentText("beforeend",data[i].apellidos);
                               let heading_4 = document.createElement('td');
-                               heading_4.insertAdjacentText("beforeend", data[i].edad);
                               let heading_5 = document.createElement('td');
-                               heading_5.insertAdjacentText("beforeend", data[i].email);
                               let heading_6 = document.createElement('td');
-                               heading_6.insertAdjacentText("beforeend", data[i].genero);
                               let heading_7 = document.createElement('td');
-                               heading_7.insertAdjacentText("beforeend", data[i].nivel)
                               let heading_8 = document.createElement('td');
-                               heading_8.insertAdjacentText("beforeend", data[i].aciertos)


                               rows_data.appendChild(heading_1);
                               rows_data.appendChild(heading_2);
                               rows_data.appendChild(heading_3);
                               rows_data.appendChild(heading_4);
                               rows_data.appendChild(heading_5);
                               rows_data.appendChild(heading_6);
                               rows_data.appendChild(heading_7);
                               rows_data.appendChild(heading_8);

                               tbody.appendChild(rows_data);

                           }

                       })


               .catch(function (err) {

               	// Log any errors
               	console.log('something went wrong', err);

               });
               }