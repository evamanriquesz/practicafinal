package com.sofiaevacris.practicafinal.service.impl;

import com.sofiaevacris.practicafinal.dto.JugadorDTO;
import com.sofiaevacris.practicafinal.model.JugadorModel;
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
    public List<JugadorModel> retrieveAll() {
        String query =
                """
                        SELECT * FROM JUGADORES 
                        """;

        List<JugadorModel> jugadorModel = jdbcTemplate.query(
                query,
                (data, rowNum) ->
                        new JugadorModel(
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
        return jugadorModel;
    }

    @Override
    public List<JugadorDTO> getJugadores(Long id){
        String query =
                """
                SELECT REST_PLATO.RESTAURANTE_ID, PLATOS.PLATO_ID, PLATOS.NOMBRE, PLATOS.PRECIO, PLATOS.FOTO, PLATOS.DESCRIPCION, PLATOS.SECCION
                FROM REST_PLATO
                RIGHT JOIN PLATOS ON (REST_PLATO.PLATO_ID = PLATOS.PLATO_ID)
                WHERE REST_PLATO.RESTAURANTE_ID = """ +id_rest;

        List<JugadorDTO> jugadores = jdbcTemplate.query(
                query,
                (rs,rowNum) ->
                        new JugadorDTO(rs.getLong("jugadorId"), rs.getLong("PLATO_ID"), rs.getString("NOMBRE"), rs.getBigDecimal("PRECIO"), rs.getString("FOTO"),rs.getString("DESCRIPCION"),rs.getString("SECCION")));
        return jugadores;
    }
    }


    @Override
    public JugadorModel retrieveJugador(Long jugadorId) {
        JugadorModel respuesta = null;
        if (jugadorRepository.existsById(jugadorId)) {
            JugadorModel jugadorModels = jugadorRepository.retrieveJugador(jugadorId);
            respuesta=jugadorModels;
        }

        return respuesta;
    }

    @Override
    public JugadorModel insertJugador(JugadorModel jugador) {
        JugadorModel j = new JugadorModel();
        j.setJugadorId(jugador.getJugadorId());
        j.setNombre(jugador.getNombre());
        j.setApellidos(jugador.getApellidos());
        j.setEdad(jugador.getEdad());
        j.setGenero(jugador.getGenero());
        j.setNivel(jugador.getNivel());
        j.setEmail(jugador.getEmail());

        JugadorModel respuesta = jugadorRepository.save(j);
        return respuesta;
    }

    @Override
    public void updateJugador(JugadorDTO j){
        Long aciertos = j.getAciertos();
        Long id= j.getJugadorId();
        jdbcTemplate.execute("UPDATE JUGADORES SET ACEIRTOS = '"+ aciertos + "'WHERE JUGADOR_ID=" + id);
    }

}
