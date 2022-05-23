package com.sofiaevacris.practicafinal.service.impl;

import com.sofiaevacris.practicafinal.model.TarjetaModel;
import com.sofiaevacris.practicafinal.repository.TarjetaRepository;
import com.sofiaevacris.practicafinal.service.TarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TarjetaServiceImpl implements TarjetaService {

    @Autowired
    private TarjetaRepository tarjetaRepository;


    //@Override
    //public List<TarjetaDTO> retrieveAll() {
    //    return StreamSupport.stream(tarjetaRepository.findAll().spliterator(), false)
    //            .map(obj -> new TarjetaDTO(
    //                    obj.getTarjetaId(),
    //                    obj.getNumeroTarjeta(),
    //                    obj.getCcv(),
    //                    obj.getUsuarioId(),
    //                    obj.getGasto()))
    //            .toList();
    //}

    @Override
    public Iterable<TarjetaModel> retrieveAll() {
        return TarjetaRepository.findAll();
    }

    @Override
    public TarjetaModel insertTarjeta(TarjetaModel tarjetaModel) {
        return null;
    }

}
