package com.sofiaevacris.practicafinal.controller;

import com.sofiaevacris.practicafinal.dto.ArtistaDTO;
import com.sofiaevacris.practicafinal.model.ArtistaModel;
import com.sofiaevacris.practicafinal.service.ArtistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ArtistaController {

    @Autowired
    private ArtistaService artistaService;

    @GetMapping("/artistas")
    public ResponseEntity<List<ArtistaDTO>> retrieveArtistas()
    {
        List<ArtistaDTO> respuesta = artistaService.retrieveAll();
        return ResponseEntity.ok().body(respuesta);
    }




    @PutMapping("/artistas/{artistaId}")
    public ResponseEntity<ArtistaModel> updateArtista(@PathVariable("artistaId") Long artistaId, @RequestBody ArtistaModel artistaModel){
        ArtistaModel newArtistaModel = artistaService.updateArtista(artistaId, artistaModel);
        if(newArtistaModel == null)
        {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok().body(newArtistaModel);

    }


    @GetMapping("/artistas/{artistaId}")
    public ResponseEntity<ArtistaModel> retrieveArtista(@PathVariable Long artistaId){
        ArtistaModel respuesta = artistaService.retrieveArtista(artistaId);
        return ResponseEntity.ok().body(respuesta);
    }





}
