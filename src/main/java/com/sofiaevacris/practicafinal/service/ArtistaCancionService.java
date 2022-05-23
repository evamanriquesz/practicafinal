package com.sofiaevacris.practicafinal.service;

import com.sofiaevacris.practicafinal.dto.ArtistaCancionDTO;
import com.sofiaevacris.practicafinal.dto.ArtistaDTO;
import com.sofiaevacris.practicafinal.model.ArtistaModel;

import java.util.List;

public interface ArtistaCancionService {
    public List<ArtistaCancionDTO> retrieveAll();
    public List<ArtistaCancionDTO> retrieveArtistaCancionAll();
    //public void deleteArtistaCancion(Long artistaId);
    public List<ArtistaCancionDTO> retrieveArtistaCancionByFavoritos(Long favoritos);


}
