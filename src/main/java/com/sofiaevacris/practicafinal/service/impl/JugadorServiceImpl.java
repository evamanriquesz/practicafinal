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
    public void insertJugador(JugadorModel jugador) {

        Long id= jugador.getJugadorId();
        String nombre= jugador.getNombre();
        String apellidos = jugador.getApellidos();
        Long edad = jugador.getEdad();
        String email = jugador.getEmail();
        String genero = jugador.getGenero();
        String nivel = jugador.getNivel();
        Long aciertos = jugador.getAciertos();

        jdbcTemplate.execute("INSERT INTO JUGADORES (JUGADOR_ID, NOMBRE, APELLIDOS, EDAD, EMAIL, GENERO, NIVEL, ACIERTOS) VALUES ("+id+",'"+nombre+"','"+apellidos+"', "+ edad+",'"+email+"','"+genero+"','"+nivel+"', "+aciertos+");");

    }

    @Override
    public List<JugadorModel> retrieveJugadoresByNivel(String nivel) {
        String query = " SELECT * FROM JUGADORES  WHERE  JUGADORES.NIVEL = '"+nivel + "'; ";


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
    public void updateJugador(JugadorDTO j){
        Long aciertos = j.getAciertos();
        Long id= j.getJugadorId();
        jdbcTemplate.execute("UPDATE JUGADORES SET ACIERTOS = "+ aciertos + "WHERE JUGADOR_ID=" + id);
    }

}
