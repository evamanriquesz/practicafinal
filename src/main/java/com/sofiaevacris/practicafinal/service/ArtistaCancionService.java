package com.sofiaevacris.practicafinal.service;

import com.sofiaevacris.practicafinal.dto.ArtistaCancionDTO;

import java.util.List;

public interface ArtistaCancionService {
    public List<ArtistaCancionDTO> retrieveAll();
    public List<ArtistaCancionDTO> retrieveArtistaCancionAll();

}
