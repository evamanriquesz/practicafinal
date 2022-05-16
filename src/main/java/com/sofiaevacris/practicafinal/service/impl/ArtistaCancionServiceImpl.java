package com.sofiaevacris.practicafinal.service.impl;

import com.sofiaevacris.practicafinal.dto.ArtistaCancionDTO;
import com.sofiaevacris.practicafinal.repository.ArtistaCancionRepository;
import com.sofiaevacris.practicafinal.service.ArtistaCancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Service
public class ArtistaCancionServiceImpl implements ArtistaCancionService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ArtistaCancionRepository artistaCancionRepository;

    @Override
    public List<ArtistaCancionDTO> retrieveAll()
    {
        String query =
                """
                        SELECT VOTOS.ARTISTA_ID, VOTOS.NOMBRE_ARTISTA, VOTOS.FAVORITOS, CANCIONES.CANCION_ID, CANCIONES.NOMBRE_CANCION, CANCIONES.ALBUM
                        FROM VOTOS 
                        LEFT JOIN CANCIONES
                        ON VOTOS.ARTISTA_ID = CANCIONES.ARTISTA_ID; 
                        """;
        List<ArtistaCancionDTO> artistaCancionDTOS = jdbcTemplate.query(
                query,
                (data, rowNum) ->
                        new ArtistaCancionDTO(
                              data.getLong("CANCION_ID"),
                              data.getString("NOMBRE_CANCION"),
                              data.getLong("ARTISTA_ID"),
                              data.getString("NOMBRE_ARTISTA"),
                              data.getString("ALBUM"),
                              data.getLong("FAVORITOS")
                        )
        );

        return artistaCancionDTOS;
    }

    //esto es exactamente igual, lo hago por si esta dando error lo de arriba
    @Override
    public List<ArtistaCancionDTO> retrieveArtistaCancionAll()
    {
        String query =
                """
                        SELECT VOTOS.ARTISTA_ID, VOTOS.NOMBRE_ARTISTA, VOTOS.FAVORITOS, CANCIONES.CANCION_ID, CANCIONES.NOMBRE_CANCION, CANCIONES.ALBUM
                        FROM VOTOS 
                        LEFT JOIN CANCIONES
                        ON VOTOS.ARTISTA_ID = CANCIONES.ARTISTA_ID; 
                        """;
        List<ArtistaCancionDTO> artistaCancionDTOS = jdbcTemplate.query(
                query,
                (data, rowNum) ->
                        new ArtistaCancionDTO(
                                data.getLong("CANCION_ID"),
                                data.getString("NOMBRE_CANCION"),
                                data.getLong("ARTISTA_ID"),
                                data.getString("NOMBRE_ARTISTA"),
                                data.getString("ALBUM"),
                                data.getLong("FAVORITOS")
                        )
        );

        return artistaCancionDTOS;
    }


}












