package com.sofiaevacris.practicafinal.service;

import com.sofiaevacris.practicafinal.dto.UsuarioDTO;
import com.sofiaevacris.practicafinal.model.UsuarioModel;
import com.sofiaevacris.practicafinal.repository.UsuarioRepository;

import java.util.List;

public interface UsuarioService {

    public List<UsuarioDTO> retrieveAll();
    public void insertUsuario(UsuarioModel usuarioModel);
}
