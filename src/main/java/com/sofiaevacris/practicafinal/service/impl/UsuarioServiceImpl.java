package com.sofiaevacris.practicafinal.service.impl;

import com.sofiaevacris.practicafinal.dto.UsuarioDTO;
import com.sofiaevacris.practicafinal.model.UsuarioModel;
import com.sofiaevacris.practicafinal.repository.UsuarioRepository;
import com.sofiaevacris.practicafinal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<UsuarioDTO> retrieveAll() {
        return StreamSupport.stream(usuarioRepository.findAll().spliterator(), false)
                .map(obj -> new UsuarioDTO(
                        obj.getUsuarioId(),
                        obj.getContra(),
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

        usuarioModel2.setUsuarioId(usuarioModel.getUsuarioId());
        usuarioModel2.setContra(usuarioModel.getContra());
        usuarioModel2.setNombre(usuarioModel.getNombre());
        usuarioModel2.setApellidos(usuarioModel.getApellidos());
        usuarioModel2.setEdad(usuarioModel.getEdad());
        usuarioModel2.setEmail(usuarioModel.getEmail());
        usuarioModel2.setTelefono(usuarioModel.getTelefono());

        UsuarioModel newUsuarioModel = usuarioRepository.save(usuarioModel2);



        return usuarioModel;
    }


}
