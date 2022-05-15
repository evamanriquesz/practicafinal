package com.sofiaevacris.practicafinal.repository;

import com.sofiaevacris.practicafinal.dto.ArtistaDTO;
import com.sofiaevacris.practicafinal.model.ArtistaModel;
import com.sofiaevacris.practicafinal.service.impl.ArtistaServiceImpl;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArtistaRepository extends CrudRepository<ArtistaModel, Long> {



    @Query("SELECT * FROM VOTOS WHERE VOTOS.ARTISTA_ID = :artistaId")
    public List<ArtistaModel> retrieveArtista(Long artistaId);


}
