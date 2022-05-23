
package com.sofiaevacris.practicafinal.service;

import com.sofiaevacris.practicafinal.dto.ArtistaDTO;
import com.sofiaevacris.practicafinal.model.ArtistaModel;

import java.util.List;

public interface ArtistaService {
    public List<ArtistaDTO> retrieveAll(); //esta clase va a ser para devolver todo lo q haya en la bbdd
    ArtistaModel updateArtista(Long artistaId, ArtistaModel artistaModel);
    public ArtistaModel retrieveArtista(Long artistaId);
    public void deleteArtistaModel(Long artistaId);
    List<ArtistaModel> retrieveArtistaByVotos(Long favoritos);

}