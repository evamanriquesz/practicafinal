package com.sofiaevacris.practicafinal.controller;

import com.sofiaevacris.practicafinal.dto.UsuarioDTO;
import com.sofiaevacris.practicafinal.model.UsuarioModel;
import com.sofiaevacris.practicafinal.service.UsuarioService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioDTO>> retrieveAll()
    {
        return ResponseEntity.ok().body(usuarioService.retrieveAll());
    }

    @PostMapping("/usuarios")
    public ResponseEntity<UsuarioModel> insertUsuario(@RequestBody UsuarioModel usuarioModel)
    {
        try{
            UsuarioModel newUsuarioModel = usuarioService.insertUsuario(usuarioModel);
            return new ResponseEntity<>(newUsuarioModel, HttpStatus.CREATED);
        } catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    }
}
