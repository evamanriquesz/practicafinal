package com.sofiaevacris.practicafinal.service.impl;

import com.sofiaevacris.practicafinal.model.TarjetaModel;
import com.sofiaevacris.practicafinal.repository.TarjetaRepository;
import com.sofiaevacris.practicafinal.service.TarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TarjetaServiceImpl implements TarjetaService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TarjetaRepository tarjetaRepository;


    @Override
    public List<TarjetaModel> retrieveTarjetas() {
        String query = """
                SELECT * FROM TARJETAS
                
                """;

        List<TarjetaModel> tarjetaModel = jdbcTemplate.query(
                query,
                (data, rowNum) -> new TarjetaModel(
                        data.getLong("TARJETA_ID"),
                        data.getString("NUMERO_TARJETA"),
                        data.getDate("FECHA_CADUCIDAD"),
                        data.getLong("CVV"),
                        data.getLong("USUARIO_ID"),
                        data.getLong("GASTO")
                )

        );
        return tarjetaModel;

    }

    @Override
    public void insertTarjeta(TarjetaModel tarjetaModel) {
        Long tarjetaId= tarjetaModel.getTarjetaId();
        String numeroTarjeta= tarjetaModel.getNumeroTarjeta();
        Date fechaCaducidad = tarjetaModel.getFechaCaducidad();
        Long cvv = tarjetaModel.getCvv();
        Long usuarioId = tarjetaModel.getUsuarioId();
        Long gasto = tarjetaModel.getGasto();


        jdbcTemplate.execute("INSERT INTO TARJETAS (TARJETA_ID, NUMERO_TARJETA, FECHA_CADUCIDAD, CVV, USUARIO_ID, GASTO) VALUES ("+tarjetaId+",'"+numeroTarjeta+"',"+fechaCaducidad+"', "+ cvv+","+usuarioId+","+gasto+");");


    }
/*
    @Override
    public Iterable<TarjetaModel> retrieveAll() {
        return TarjetaRepository.findAll();
    }

    @Override
    public TarjetaModel insertTarjeta(TarjetaModel tarjetaModel) {
        return null;
    }
*/
}
