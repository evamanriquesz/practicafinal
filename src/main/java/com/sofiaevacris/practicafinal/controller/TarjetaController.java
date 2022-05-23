package com.sofiaevacris.practicafinal.controller;

import com.sofiaevacris.practicafinal.dto.TarjetaDTO;
import com.sofiaevacris.practicafinal.model.JugadorModel;
import com.sofiaevacris.practicafinal.model.TarjetaModel;
import com.sofiaevacris.practicafinal.service.TarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TarjetaController {

    @Autowired
    private TarjetaService tarjetaService;

    @GetMapping("/tarjetas")
    public ResponseEntity<List<TarjetaModel>> retrieveTarjetas() {
        List<TarjetaModel> respuesta = tarjetaService.retrieveTarjetas();

        return ResponseEntity.ok().body(respuesta);
    }


    @PostMapping("/tarjetas")
    public ResponseEntity<String> createTarjeta(@RequestBody TarjetaModel tarjetaModel)
    {
        tarjetaService.insertTarjeta(tarjetaModel);
        return ResponseEntity.ok().body("Tarjeta añadida");
    }
}

