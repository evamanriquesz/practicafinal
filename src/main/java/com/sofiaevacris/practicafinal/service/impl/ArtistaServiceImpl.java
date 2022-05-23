package com.sofiaevacris.practicafinal.service.impl;

import com.sofiaevacris.practicafinal.dto.ArtistaCancionDTO;
import com.sofiaevacris.practicafinal.dto.ArtistaDTO;
import com.sofiaevacris.practicafinal.model.ArtistaModel;
import com.sofiaevacris.practicafinal.repository.ArtistaRepository;
import com.sofiaevacris.practicafinal.service.ArtistaService;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
 import java.util.List;

@Service
public class ArtistaServiceImpl implements ArtistaService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ArtistaRepository artistaRepository;

    @Override
    public List<ArtistaDTO> retrieveAll()
    {
        String query =
                """
                        SELECT VOTOS.ARTISTA_ID, VOTOS.NOMBRE_ARTISTA, VOTOS.FAVORITOS
                         FROM VOTOS 
                        """;

        List <ArtistaDTO> artistaDTOS = jdbcTemplate.query(
                query,
                (data, rowNum) ->
                        new ArtistaDTO(
                                data.getLong("ARTISTA_ID"),
                                data.getString("NOMBRE_ARTISTA"),
                                data.getLong("FAVORITOS")
                        )
        );

        return  artistaDTOS;

    }

    @Override
    public ArtistaModel updateArtista(Long artistaId, ArtistaModel artistaModel) {
        if(artistaRepository.existsById(artistaId))
        {
            return artistaRepository.save(artistaModel);
        }else{
            return null;
        }
    }


    @Override
    public ArtistaModel retrieveArtista(Long artistaId)
    {
        ArtistaModel respuesta = null;
        if(artistaRepository.existsById(artistaId)){
            List<ArtistaModel> artistaModels = artistaRepository.retrieveArtista(artistaId);

            for(ArtistaModel artistaModel:artistaModels)
            {
                respuesta = artistaModel;
            }
        }
        return respuesta;
    }

    @Override
    public void deleteArtistaModel(Long artistaId)
    {
        artistaRepository.deleteById(artistaId);
    }

    @Override
    public List<ArtistaModel> retrieveArtistaByFavoritos(Long favoritos) {
        String query =" SELECT VOTOS.ARTISTA_ID, VOTOS.NOMBRE_ARTISTA, VOTOS.FAVORITOS FROM VOTOS WHERE VOTOS.FAVORITOS >= "+favoritos ;


        List<ArtistaModel> artistaModel = jdbcTemplate.query(
                query,
                (data, rowNum) ->
                        new ArtistaModel(
                                data.getLong("ARTISTA_ID"),
                                data.getString("NOMBRE_ARTISTA"),
                                data.getLong("FAVORITOS")
                        )
        );
        return artistaModel;
    }

}








