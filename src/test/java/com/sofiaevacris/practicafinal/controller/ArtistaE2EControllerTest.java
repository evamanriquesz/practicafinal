package com.sofiaevacris.practicafinal.controller;

import com.sofiaevacris.practicafinal.dto.ArtistaDTO;
import com.sofiaevacris.practicafinal.repository.ArtistaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.BDDAssertions.then;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.stream.StreamSupport;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ArtistaE2EControllerTest {

    @Autowired
    private ArtistaRepository artistaRepository;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    //test del enpoint @GetMapping("/artistas")

    @Test
    public void artistaEndpointTest()
    {
        //given

        List<ArtistaDTO> artistaDTOS = StreamSupport.stream(artistaRepository.findAll().spliterator(), false)
                .map(obj -> new ArtistaDTO(
                        obj.getArtistaId(),
                        obj.getNombreArtista(),
                        obj.getFavoritos()))
                .toList();

        //when

        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/artistas";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        //la llamada al endpoint nos va a responder un ResponseEntity con una lista de ArtistaDTO

        ResponseEntity<List<ArtistaDTO>> result = testRestTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<ArtistaDTO>>(){} //cuando el testRestTemplate reciba un json como List de ArtistaDTO, lo convierta otra vez a elementos de java para compararlos
        );
    }



}
