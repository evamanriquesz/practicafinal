package com.sofiaevacris.practicafinal.controller;

import com.sofiaevacris.practicafinal.dto.TarjetaDTO;
import com.sofiaevacris.practicafinal.model.TarjetaModel;
import com.sofiaevacris.practicafinal.service.TarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TarjetaController {

    @Autowired
    private TarjetaService tarjetaService;

    @GetMapping("/tarjeta")
    public ResponseEntity<Iterable<TarjetaModel>> retrieveAll() {
        return ResponseEntity.ok().body(TarjetaService.retrieveAll());
    }

}

