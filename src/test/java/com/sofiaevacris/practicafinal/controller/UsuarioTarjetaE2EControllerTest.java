
package com.sofiaevacris.practicafinal.controller;



import com.sofiaevacris.practicafinal.dto.ArtistaCancionDTO;
import com.sofiaevacris.practicafinal.dto.UsuarioTarjetaDTO;
import com.sofiaevacris.practicafinal.dto.UsuarioTarjetaDetalladoDTO;
import org.junit.jupiter.api.Test;
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
public class UsuarioTarjetaE2EControllerTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    //test del endpoint @GetMapping("/clientes")

    @Test
    public void usuarioTarjetaEndpointTest()
    {
        //given
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


        //when
        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/clientes";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        //la llamada al endpoint de la parte 2 nos va a responder un ResponseEntity con una lista de UsuarioTarjetDTO
        ResponseEntity<List<UsuarioTarjetaDTO>> result = testRestTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<UsuarioTarjetaDTO>>(){} //cuando el testRestTemplate reciba un json como List de UsuarioTarjetaDTO, lo convierta otra vez a elementos de java para compararlos
        );



        //then

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK); //te permite acar el valor de algo y compararlo con otra cosa. en este caso voy a sacar el valor de status code pq espero q sea 200
        then(result.getBody()).isEqualTo(usuarioTarjetaDTOS); //esto hará uso del equals de UsuaarioTarjetDTO, serán iguales si tienen el mismo usuarioId

    }


    @Test
    public void usuarioTarjetaDetalladoEndpointTest()
    {
        //given
        String query =
                """
                        SELECT USUARIOS.USUARIO_ID, USUARIOS.NOMBRE, USUARIOS.APELLIDOS, TARJETAS.NUMERO_TARJETA, TARJETAS.FECHA_CADUCIDAD, TARJETAS.CVV, TARJETAS.GASTO
                        FROM USUARIOS 
                        LEFT JOIN TARJETAS
                        ON USUARIOS.USUARIO_ID = TARJETAS.USUARIO_ID; 
                        """;
        List<UsuarioTarjetaDetalladoDTO> usuarioTarjetaDetalladoDTOS = jdbcTemplate.query(
                query,
                (data, rowNum) ->
                        new UsuarioTarjetaDetalladoDTO(
                                data.getLong("USUARIO_ID"),
                                data.getString("NOMBRE"),
                                data.getString("APELLIDOS"),
                                data.getString("NUMERO_TARJETA"),
                                data.getString("FECHA_CADUCIDAD"),
                                data.getLong("CVV"),
                                data.getLong("GASTO")
                        )
        );



        //when
        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/clientes/detallado";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        //la llamada al endpoint de la parte 2 nos va a responder un ResponseEntity con una lista de UsuarioTarjetaDetalladoDTO
        ResponseEntity<List<UsuarioTarjetaDetalladoDTO>> result = testRestTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<UsuarioTarjetaDetalladoDTO>>(){} //cuando el testRestTemplate reciba un json como List de UsuarioTarjetaDTO, lo convierta otra vez a elementos de java para compararlos
        );



        //then

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK); //te permite acar el valor de algo y compararlo con otra cosa. en este caso voy a sacar el valor de status code pq espero q sea 200
        then(result.getBody()).isEqualTo(usuarioTarjetaDetalladoDTOS); //esto hará uso del equals de UsuaarioTarjetaDetalladoDTO, serán iguales si tienen el mismo usuarioId

    }




}

