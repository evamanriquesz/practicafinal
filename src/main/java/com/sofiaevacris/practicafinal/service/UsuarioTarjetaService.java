package com.sofiaevacris.practicafinal.service;

import com.sofiaevacris.practicafinal.dto.UsuarioTarjetaDTO;
import com.sofiaevacris.practicafinal.dto.UsuarioTarjetaDetalladoDTO;

import java.util.List;

public interface UsuarioTarjetaService {

    public List<UsuarioTarjetaDTO> retrieveAll();
    public List<UsuarioTarjetaDetalladoDTO> retrieveAllDetallado();
}
