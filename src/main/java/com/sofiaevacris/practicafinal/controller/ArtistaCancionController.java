package com.sofiaevacris.practicafinal.controller;


import com.sofiaevacris.practicafinal.dto.ArtistaCancionDTO;
import com.sofiaevacris.practicafinal.service.ArtistaCancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ArtistaCancionController {

    @Autowired
    private ArtistaCancionService artistaCancionService;

    @GetMapping("/artistaCancion")
    public ResponseEntity<HashMap<String, List<String>>> retrieveAll()
    {
        List<ArtistaCancionDTO> artistaCancionDTOS = new ArrayList<ArtistaCancionDTO>();
        artistaCancionDTOS = artistaCancionService.retrieveAll();

        HashMap<String, List<String>> artistaCancions = new HashMap<>();
        List <String> canciones = new ArrayList<>();

        for(ArtistaCancionDTO artistaCancionDTO : artistaCancionDTOS)
        {
            if(artistaCancions.containsKey(artistaCancionDTO.getNombreArtista()))
            {
                canciones = artistaCancions.get(artistaCancionDTO.getNombreArtista());
            }else{
                canciones = new ArrayList<>();
                artistaCancions.put(artistaCancionDTO.getNombreArtista(), new ArrayList<>());
            }

            if(artistaCancionDTO.getNombreCancion()!= null)
            {
                canciones.add(artistaCancionDTO.getNombreCancion());
                artistaCancions.replace(artistaCancionDTO.getNombreArtista(), canciones);
            }else{
                artistaCancions.put(artistaCancionDTO.getNombreArtista(), null);
            }
        }

        return ResponseEntity.ok().body(artistaCancions);
    }

    @GetMapping("/artistaCancionAll")
    public ResponseEntity<List<ArtistaCancionDTO>> retrieveArtistaCancionAll()
    {
        List<ArtistaCancionDTO> respuesta = artistaCancionService.retrieveAll();
        return ResponseEntity.ok().body(respuesta);
    }

}

















