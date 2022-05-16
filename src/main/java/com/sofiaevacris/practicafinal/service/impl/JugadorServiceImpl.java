package com.sofiaevacris.practicafinal.service.impl;

import com.sofiaevacris.practicafinal.dto.ArtistaDTO;
import com.sofiaevacris.practicafinal.dto.JugadorDTO;
import com.sofiaevacris.practicafinal.model.ArtistaModel;
import com.sofiaevacris.practicafinal.model.JugadorModel;
import com.sofiaevacris.practicafinal.repository.ArtistaRepository;
import com.sofiaevacris.practicafinal.repository.JugadorRepository;
import com.sofiaevacris.practicafinal.service.JugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JugadorServiceImpl implements JugadorService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private JugadorRepository jugadorRepository;

    @Override
    public List<JugadorDTO> retrieveAll()
    {
        String query =
                """
                        SELECT * FROM JUGADORES 
                        """;

        List <JugadorDTO> jugadorDTOS = jdbcTemplate.query(
                query,
                (data, rowNum) ->
                        new JugadorDTO(
                                data.getLong("JUGADOR_ID"),
                                data.getString("NOMBRE"),
                                data.getString("APELLIDOS"),
                                data.getLong("EDAD"),
                                data.getString("GENERO"),
                                data.getString("EMAIL"),
                                data.getString("NIVEL"),
                                data.getLong("ACIERTOS")
                        )
        );

        return  jugadorDTOS;

    }
    

    @Override
    public JugadorModel retrieveJugador(Long jugadorId)
    {
        JugadorModel respuesta = null;
        if(jugadorRepository.existsById(jugadorId)){
            List<JugadorModel> jugadorModels = jugadorRepository.retrieveJugador(jugadorId);

            for(JugadorModel jugadorModel:jugadorModels)
            {
                respuesta = jugadorModel;
            }
        }

        return respuesta;
    }
}
