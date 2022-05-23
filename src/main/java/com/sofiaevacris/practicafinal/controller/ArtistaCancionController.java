package com.sofiaevacris.practicafinal.controller;


import com.sofiaevacris.practicafinal.dto.ArtistaCancionDTO;
import com.sofiaevacris.practicafinal.dto.CancionDTO;
import com.sofiaevacris.practicafinal.model.JugadorModel;
import com.sofiaevacris.practicafinal.service.ArtistaCancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ArtistaCancionController {

    @Autowired
    private ArtistaCancionService artistaCancionService;


    //este igual lo borro
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

    @GetMapping("/artistaCancionAll/{artistaId}")
    public ResponseEntity<List<ArtistaCancionDTO>> retrieveCancionesByArtista(@PathVariable("artistaId") Long artistaId)
    {
        List<ArtistaCancionDTO> artistaCancionDTOEncontrado = new ArrayList<ArtistaCancionDTO>();
        List<ArtistaCancionDTO> artistaCancionDTOS = artistaCancionService.retrieveAll();
        for (ArtistaCancionDTO artistaCancionDTO : artistaCancionDTOS)
        {
            if(artistaCancionDTO.getArtistaId() == artistaId)
            {
                artistaCancionDTOEncontrado.add(artistaCancionDTO);
            }
        }
       return ResponseEntity.ok().body(artistaCancionDTOEncontrado);
    }

    /*
    @DeleteMapping("/artistaCancionAll/{artistaId}")
    public ResponseEntity<ArtistaCancionDTO> deleteArtistaCancionDTO(@PathVariable("artistaId") Long artistaId)
    {
        artistaCancionService.deleteArtistaCancion(artistaId);
        return ResponseEntity.noContent().build();

    }
    */

    @GetMapping("/artistaCancionAll/favoritos/{favoritos}")
    public ResponseEntity<List<ArtistaCancionDTO>> retrieveCancionesByFavoritos(@PathVariable("favoritos") Long favoritos)
    {
        List<ArtistaCancionDTO> respuesta = artistaCancionService.retrieveArtistaCancionByFavoritos(favoritos);
        return ResponseEntity.ok().body(respuesta);
    }
}

















