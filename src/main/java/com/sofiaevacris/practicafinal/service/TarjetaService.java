package com.sofiaevacris.practicafinal.service;

import com.sofiaevacris.practicafinal.dto.TarjetaDTO;
import com.sofiaevacris.practicafinal.dto.UsuarioDTO;
import com.sofiaevacris.practicafinal.model.TarjetaModel;
import com.sofiaevacris.practicafinal.model.UsuarioModel;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TarjetaService {
    // public List<TarjetaDTO> retrieveAll();

    public List<TarjetaModel> retrieveTarjetas();

    public void insertTarjeta(TarjetaModel tarjetaModel);
}
