package com.sofiaevacris.practicafinal.controller;

import com.sofiaevacris.practicafinal.dto.UsuarioDTO;
import com.sofiaevacris.practicafinal.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.BDDAssertions.then;

import java.util.List;
import java.util.stream.StreamSupport;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsuarioE2EControllerTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    //test del enpoint @GetMapping("/usuarios")

    @Test
    public void usuarioEndpointTest()
    {
        //given

        List<UsuarioDTO> usuarioDTOS = StreamSupport.stream(usuarioRepository.findAll().spliterator(), false)
                .map(obj -> new UsuarioDTO(
                        obj.getUsuarioId(),
                        obj.getContra(),
                        obj.getNombre(),
                        obj.getApellidos(),
                        obj.getEdad(),
                        obj.getEmail(),
                        obj.getTelefono()))
                .toList();

        //when

        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/usuarios";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        //la llamada al endpoint nos va a responder un ResponseEntity con una lista de UsuarioDTO

        ResponseEntity<List<UsuarioDTO>> result = testRestTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<UsuarioDTO>>(){} //cuando el testRestTemplate reciba un json como List de UsuarioDTO, lo convierta otra vez a elementos de java para compararlos
        );
    }



}
