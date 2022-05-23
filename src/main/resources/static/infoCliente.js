function mostrar(){
    mostrarInfoClientes();
}

async function mostrarInfoClientes(){
    let res = await fetch("/api/v1/clientes",{
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
    }});

    if (res.ok){
    const data= await res.json();
    div=document.getElementById("mostrarInfoCliente");

   let usuario=data[0].usuario_id;
   let nombre=data[0].nombre;
   let apellidos=data[0].apellidos;
   let email=data[0].email;
   let tarjeta = data[0].n_tarjeta;


   const mostrarInfoCliente = document.getElementById("mostrarInfoCliente");
   let table = document.createElement('table');
   let thead = document.createElement('thead');
   let tbody = document.createElement('tbody');

   table.appendChild(thead);
   table.appendChild(tbody);

   mostrarInfoCliente.appendChild(table);

   let row_1 = document.createElement('tr');

   let heading_2 = document.createElement('th');
   heading_2.insertAdjacentText("beforeend","Id");
   let heading_3 = document.createElement('th');
   heading_3.insertAdjacentText("beforeend","Nombre");
   let heading_4 = document.createElement('th');
   heading_4.insertAdjacentText("beforeend","Apellidos");
   let heading_5 = document.createElement('th');
   heading_5.insertAdjacentText("beforeend","Email");
   let heading_6 = document.createElement('th');
   heading_6.insertAdjacentText("beforeend","Numero de la tarjeta");


    row_1.appendChild(heading_2);
    row_1.appendChild(heading_3);
    row_1.appendChild(heading_4);
    row_1.appendChild(heading_5);
    row_1.appendChild(heading_6);

    thead.appendChild(row_1);

let rows_data = document.createElement('tr');


    let heading_22 = document.createElement('td');
    heading_22.insertAdjacentText("beforeend",usuario);
    let heading_33 = document.createElement('td');
    heading_33.insertAdjacentText("beforeend",nombre);
    let heading_44 = document.createElement('td');
    heading_44.insertAdjacentText("beforeend", apellidos);
let heading_55 = document.createElement('td');
    heading_55.insertAdjacentText("beforeend", email);
let heading_66 = document.createElement('td');
    heading_66.insertAdjacentText("beforeend", tarjeta);


    rows_data.appendChild(heading_22);
    rows_data.appendChild(heading_33);
    rows_data.appendChild(heading_44);
    rows_data.appendChild(heading_55);
    rows_data.appendChild(heading_66);

    tbody.appendChild(rows_data);



    }
}