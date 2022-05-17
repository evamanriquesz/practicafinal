package com.sofiaevacris.practicafinal.service;

import com.sofiaevacris.practicafinal.dto.ProductoDTO;
import com.sofiaevacris.practicafinal.model.ArtistaModel;
import com.sofiaevacris.practicafinal.model.ProductoModel;

import java.util.List;

public interface ProductoService {

    public List<ProductoDTO> retrieveAll();
    ProductoModel updateProducto(Long productoId, ProductoModel productoModel);
    public ProductoModel retrieveProducto(Long productoId);

}
