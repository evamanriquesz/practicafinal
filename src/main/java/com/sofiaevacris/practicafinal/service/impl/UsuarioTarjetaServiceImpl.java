package com.sofiaevacris.practicafinal.service.impl;

import com.sofiaevacris.practicafinal.dto.ArtistaCancionDTO;
import com.sofiaevacris.practicafinal.dto.UsuarioTarjetaDTO;
import com.sofiaevacris.practicafinal.repository.ArtistaCancionRepository;
import com.sofiaevacris.practicafinal.repository.UsuarioTarjetaRepository;
import com.sofiaevacris.practicafinal.service.UsuarioTarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioTarjetaServiceImpl implements UsuarioTarjetaService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Autowired
    private UsuarioTarjetaRepository usuarioTarjetaRepository;

    @Override
    public List<UsuarioTarjetaDTO> retrieveAll(){
        String query =
                """
                        SELECT USUARIOS.USUARIO_ID, USUARIOS.NOMBRE, USUARIOS.APELLIDOS, USUARIOS.EMAIL, TARJETAS.NUMERO_TARJETA
                        FROM USUARIOS 
                        LEFT JOIN TARJETAS
                        ON USUARIOS.USUARIO_ID = TARJETAS.USUARIO_ID; 
                        """;
        List<UsuarioTarjetaDTO> usuarioTarjetaDTOS = jdbcTemplate.query(
                query,
                (data, rowNum) ->
                        new UsuarioTarjetaDTO(
                                data.getLong("USUARIO_ID"),
                                data.getString("NOMBRE"),
                                data.getString("APELLIDOS"),
                                data.getString("EMAIL"),
                                data.getString("NUMERO_TARJETA")
                        )
        );

        return usuarioTarjetaDTOS;
    }
}
