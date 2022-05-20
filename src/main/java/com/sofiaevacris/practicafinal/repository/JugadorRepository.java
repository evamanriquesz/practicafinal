package com.sofiaevacris.practicafinal.repository;

import com.sofiaevacris.practicafinal.model.ArtistaModel;
import com.sofiaevacris.practicafinal.model.JugadorModel;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JugadorRepository extends CrudRepository<JugadorModel, Long> {

    @Query("SELECT * FROM JUGADORES WHERE JUGADORES.JUGADOR_ID = :jugadorId")
    public JugadorModel retrieveJugador(Long jugadorId);

}
