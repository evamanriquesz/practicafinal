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
    public ResponseEntity<String> insertUsuario(@RequestBody UsuarioModel usuarioModel)
    {
       usuarioService.insertUsuario(usuarioModel);
       return ResponseEntity.ok().body("usuario registrado");
    }
}
