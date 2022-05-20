
function registrarUsuario(usuarioId, contra, nombre, apellidos, edad, email, telefono, regis)
{
    console.log("Registrar usuario");
    registrarU(usuarioId, contra, nombre, apellidos, edad, email, telefono, regis);
}


async function registrarU(usuarioId, contra, nombre, apellidos, edad, email, telefono, regis){
    event.preventDefault();
    if (usuarioId == "" || contra == "" || nombre == "" || apellidos == "" || edad == "" || email == "" || telefono == ""){
        alert("Por favor, rellene todos los campos.");
    }else if(!validarEmail(email)){
        alert("El email introducido no es válido. Introduzca otro.");
    }else{
        // Comprobación de validez del nombre de usuario
        //let usuarioIdNoExiste = await usuarioIdNoExiste(usuarioId); //arreglar esto!!!
        let usuarioIdNoExiste = true;

        if (usuarioIdNoExiste){
            const dataObj = {
                "usuarioId" : usuarioId,
                 "contra" : contra,
                 "nombre" : nombre,
                 "apellidos" : apellidos,
                 "edad" : edad,
                 "email" : email,
                 "telefono" : telefono
            };


            // Se inserta el nuevo usuario en la BBDD si todas las comprobaciones han ido OK
            let res = await fetch("/api/v1/usuarios",{
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',

                },
                body: JSON.stringify(dataObj)
            });

             console.log(res);

            regis.innerHTML = "";

            if (res.status == 201){
                alert("¡Bienvenido!");
                location.replace("fanstore.html");
            }else{
                alert("¡Vaya! Parece que algo ha ido mal :(");
            }

        }else{
            regis.innerHTML = '<p style="color:red;">El ID de usuario ya existe. Elija otro.</p>';
        }
    }
}


/////////////////////////////////////////////////////////
//FUNCION PARA VALIDAR EL EMAIL
/////////////////////////////////////////////////////////

function validarEmail(email){
    //REGEXP EMAIL
    const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    return emailRegex.test(email);
}


////////////////////////////////////////////////////
//FUNCION PARA VER SI EL USUARIO YA EXISTE
//////////////////////////////////////////////

async function usuarioIdNoExiste(usuarioIdNoExiste){

        let res = await fetch("/api/v1/usuarios",{
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
        }});

        let noExiste = true;
        if (res.status == 200){
            const data = await res.json();

            for (let i = 0; i<data.length; i++){
                let user = data[i];
                let usuarioId1 = user["usuarioId"];
                if (usuarioId1 == usuarioIdNoExiste){
                    noExiste = false;
                }
            }

            return noExiste;

        }else{
            alert("¡Vaya! No se ha podido resolver tu petición");
            return false;
        }
}
