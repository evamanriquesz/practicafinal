package com.sofiaevacris.practicafinal.service.impl;

import com.sofiaevacris.practicafinal.dto.UsuarioDTO;
import com.sofiaevacris.practicafinal.model.UsuarioModel;
import com.sofiaevacris.practicafinal.repository.UsuarioRepository;
import com.sofiaevacris.practicafinal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public List<UsuarioDTO> retrieveAll() {
        return StreamSupport.stream(usuarioRepository.findAll().spliterator(), false)
                .map(obj -> new UsuarioDTO(
                        obj.getUsuarioId(),
                        obj.getNombre(),
                        obj.getApellidos(),
                        obj.getEdad(),
                        obj.getEmail(),
                        obj.getTelefono()))
                .toList();
    }

    @Override
    public UsuarioModel insertUsuario(UsuarioModel usuarioModel) {
        UsuarioModel usuarioModel2 = new UsuarioModel();
        // Como es un POST, no pasamos el ID (es un Long @Id, se autoincrementa solo)
        usuarioModel2.setNombre(usuarioModel2.getNombre());
        usuarioModel2.setApellidos(usuarioModel2.getApellidos());
        usuarioModel2.setEdad(usuarioModel2.getEdad());
        usuarioModel2.setEmail(usuarioModel2.getEmail());
        usuarioModel2.setTelefono(usuarioModel2.getTelefono());

        UsuarioModel newUsuarioModel = usuarioRepository.save(usuarioModel2);

        return newUsuarioModel;
    }
}
