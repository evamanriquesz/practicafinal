package com.sofiaevacris.practicafinal.service;

import com.sofiaevacris.practicafinal.dto.JugadorDTO;
import com.sofiaevacris.practicafinal.model.JugadorModel;

import java.util.List;

public interface JugadorService {

    public List<JugadorModel> retrieveAll(); //esta clase va a ser para devolver todo lo q haya en la bbdd
    public JugadorModel retrieveJugador(Long jugadorId);
    public void insertJugador (JugadorModel jugador);

    void updateJugador(JugadorDTO j);

}

