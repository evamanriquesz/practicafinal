//////////////////////////////////////////////////////////////////////////
//COMPROBAR EL USUARIO Y LA CONTRASEÑA
/////////////////////////////////////////////////////////////////////////

function comprobarUsuario(usuarioId,contra,inicioSes)
{
    console.log("Se esta ejecutando Comprobar Usuario");
    comprobarU(usuarioId,contra,inicioSes);
}


async function comprobarU(usuarioId,contra,inicioSes){
    event.preventDefault();
    let dentro = false;
    console.log(usuarioId);
    console.log(contra);

    if (usuarioId== "" || contra == ""){
        alert("Por favor, rellene todos los campos");
    }else{
        let res = await fetch("/api/v1/usuarios",{
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
        }});

        if (res.status == 200){
            const data = await res.json();
            console.log(data);
            for (let i = 0; i<data.length; i++){
                let usuario1 = data[i];
                let usuarioId1 = usuario1["usuarioId"];
                let contra1 = usuario1["contra"];
                console.log(usuarioId1);
                console.log(contra1);
                //SET del parámetro user ID
                //sessionStorage.setItem("usuarioId", usuarioId); //creo q no hace falta
                console.log(dentro);
                if (usuarioId1 == usuarioId && contra1 == contra ){
                    dentro = true;
                      console.log("Ahora si" + dentro);
                    break;
                }
            }
            if (dentro){
                location.replace("fanstore.html");
            }else{
                inicioSes.innerHTML = '<p style="color:red;">Error en los datos introducidos. Vuelva a intentarlo.</p>';
            }
        }else{
            alert("¡Vaya! No se ha podido resolver tu petición.");
        }
    }


}