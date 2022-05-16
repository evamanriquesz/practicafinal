package com.sofiaevacris.practicafinal.controller;

import com.sofiaevacris.practicafinal.dto.ArtistaDTO;
import com.sofiaevacris.practicafinal.dto.JugadorDTO;
import com.sofiaevacris.practicafinal.model.ArtistaModel;
import com.sofiaevacris.practicafinal.model.JugadorModel;
import com.sofiaevacris.practicafinal.service.ArtistaService;
import com.sofiaevacris.practicafinal.service.JugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/jugadores/{jugadoresId}")
    public ResponseEntity<JugadorModel> retrieveJugador(@PathVariable Long jugadorId){
        JugadorModel respuesta = jugadorService.retrieveJugador(jugadorId);
        return ResponseEntity.ok().body(respuesta);
    }
}
