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
    public ResponseEntity<List<JugadorModel>> retrieveArtistas()
    {
        List<JugadorModel> respuesta = jugadorService.retrieveAll();
        return ResponseEntity.ok().body(respuesta);
    }


    @GetMapping("/jugadores/{jugadorId}")
    public ResponseEntity<JugadorModel> retrieveJugador(@PathVariable Long jugadorId){
        JugadorModel respuesta = jugadorService.retrieveJugador(jugadorId);
        return ResponseEntity.ok().body(respuesta);
    }


    @PostMapping("/jugador/update/{id}")
    public ResponseEntity<String> updateJugador (@RequestBody JugadorDTO j)
    {
        jugadorService.updateJugador(j);

        return ResponseEntity.ok().body("Jugador actualizado");
    }


    @PostMapping("/jugadores")
    public ResponseEntity<String> createJugador(@RequestBody JugadorModel jugadorModel)
    {
        jugadorService.insertJugador(jugadorModel);
        return ResponseEntity.ok().body("Jugador añadido");
    }

    @GetMapping("/jugadores/nivel/{nivel}")
    public ResponseEntity<List<JugadorModel>> retrieveJugadoresByNivel(@PathVariable String nivel)
    {
        List<JugadorModel> respuesta = jugadorService.retrieveJugadoresByNivel(nivel);
        return ResponseEntity.ok().body(respuesta);
    }



}
