package com.sofiaevacris.practicafinal.controller;

import com.sofiaevacris.practicafinal.dto.ArtistaDTO;
import com.sofiaevacris.practicafinal.dto.JugadorDTO;
import com.sofiaevacris.practicafinal.model.ArtistaModel;
import com.sofiaevacris.practicafinal.model.JugadorModel;
import com.sofiaevacris.practicafinal.model.UsuarioModel;
import com.sofiaevacris.practicafinal.service.ArtistaService;
import com.sofiaevacris.practicafinal.service.JugadorService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class JugadorController {

    @Autowired
    private JugadorService jugadorService;

    @GetMapping("/jugadores")
    public ResponseEntity<List<JugadorDTO>> retrieveArtistas()
    {
        List<JugadorDTO> respuesta = jugadorService.retrieveAll();
        return ResponseEntity.ok().body(respuesta);
    }


    @PostMapping("/jugador")
    public ResponseEntity<String> insertJugador(@RequestBody JugadorModel jugadorModel)
    {
        /*
        try{
            JugadorModel newJugador = jugadorService.insertJugador(jugadorModel);
            return new ResponseEntity<>(newJugador, HttpStatus.CREATED);
        } catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }*/

        jugadorService.insertJugador(jugadorModel);
        return ResponseEntity.ok().body("jugador incluido");

    }
}
