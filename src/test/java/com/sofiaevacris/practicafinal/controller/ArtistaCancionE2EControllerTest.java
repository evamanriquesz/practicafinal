package com.sofiaevacris.practicafinal.controller;

import org.junit.jupiter.api.Test;
import com.sofiaevacris.practicafinal.dto.ArtistaCancionDTO;
import com.sofiaevacris.practicafinal.repository.ArtistaCancionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.jdbc.core.convert.JdbcValue;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.BDDAssertions.then;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.stream.StreamSupport;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ArtistaCancionE2EControllerTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    //test del endpoint @GetMapping("/artistaCancionAll")

    @Test
    public void artistaCancionEndpointTest()
    {
        //given
        String query =
                """
                        SELECT VOTOS.ARTISTA_ID, VOTOS.NOMBRE_ARTISTA, VOTOS.FAVORITOS, CANCIONES.CANCION_ID, CANCIONES.NOMBRE_CANCION, CANCIONES.ALBUM
                        FROM VOTOS 
                        LEFT JOIN CANCIONES
                        ON VOTOS.ARTISTA_ID = CANCIONES.ARTISTA_ID; 
                        """;
        List<ArtistaCancionDTO> artistaCancionDTOS = jdbcTemplate.query(
                query,
                (data, rowNum) ->
                        new ArtistaCancionDTO(
                                data.getLong("CANCION_ID"),
                                data.getString("NOMBRE_CANCION"),
                                data.getLong("ARTISTA_ID"),
                                data.getString("NOMBRE_ARTISTA"),
                                data.getString("ALBUM"),
                                data.getLong("FAVORITOS")
                        )
        );

        //when
        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/artistaCancionAll";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        //la llamada al endpoint de la parte 2 nos va a responder un ResponseEntity con una lista de ArtistaCancionDTO
        ResponseEntity<List<ArtistaCancionDTO>> result = testRestTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<ArtistaCancionDTO>>(){} //cuando el testRestTemplate reciba un json como List de ArtistaCancionDTO, lo convierta otra vez a elementos de java para compararlos
        );

        //then
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK); //te permite acar el valor de algo y compararlo con otra cosa. en este caso voy a sacar el valor de status code pq espero q sea 200
        then(result.getBody()).isEqualTo(artistaCancionDTOS); //esto hará uso del equals de ArtistaCancionDTO, serán iguales si tienen el mismo artistaId

    }

}
