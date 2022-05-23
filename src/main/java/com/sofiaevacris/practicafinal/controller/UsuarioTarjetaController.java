package com.sofiaevacris.practicafinal.controller;

import com.sofiaevacris.practicafinal.dto.ArtistaCancionDTO;
import com.sofiaevacris.practicafinal.dto.UsuarioTarjetaDTO;
import com.sofiaevacris.practicafinal.service.ArtistaCancionService;
import com.sofiaevacris.practicafinal.service.UsuarioTarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UsuarioTarjetaController {
    @Autowired
    private UsuarioTarjetaService usuarioTarjetaService;

    @GetMapping("/clientes")
    public ResponseEntity<List<UsuarioTarjetaDTO>> retrieveArtistaCancionAll()
    {
        List<UsuarioTarjetaDTO> respuesta = usuarioTarjetaService.retrieveAll();
        return ResponseEntity.ok().body(respuesta);
    }
}
