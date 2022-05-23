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

    //para mostrarlo alfinal de las partidas
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
        /*
        JugadorModel j = new JugadorModel();
        j.setJugadorId(jugador.getJugadorId());
        j.setNombre(jugador.getNombre());
        j.setApellidos(jugador.getApellidos());
        j.setEdad(jugador.getEdad());
        j.setGenero(jugador.getGenero());
        j.setNivel(jugador.getNivel());
        j.setEmail(jugador.getEmail());
        j.setAciertos(jugador.getAciertos());


         */
        //jdbcTemplate.execute("INSERT INTO JUGADORES(JUGADOR_ID, NOMBRE, APELLIDOS, EDAD, GENERO, EMAIL, NIVEL, ACIERTOS) VALUES (" +jugador.getJugadorId()+ ", "+jugador.getNombre() +", " + jugador.getApellidos() + ", "+ jugador.getEdad() + ", " + jugador.getGenero() + ", " + jugador.getEmail() + ", " + jugador.getNivel() + ", "+ jugador.getAciertos() +");");

        //JugadorModel respuesta = jugadorRepository.save(j);
        return jugadorRepository.save(jugador);
    }

    @Override
    public void updateJugador(JugadorDTO j){
        Long aciertos = j.getAciertos();
        Long id= j.getJugadorId();
        jdbcTemplate.execute("UPDATE JUGADORES SET ACIERTOS = "+ aciertos + "WHERE JUGADOR_ID=" + id);
    }

}
