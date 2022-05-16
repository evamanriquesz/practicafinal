package com.sofiaevacris.practicafinal.repository;

import com.sofiaevacris.practicafinal.dto.ArtistaCancionDTO;
import org.springframework.data.repository.CrudRepository;

public interface ArtistaCancionRepository extends CrudRepository<ArtistaCancionDTO, Long> {
}
