package com.sofiaevacris.practicafinal.repository;

import com.sofiaevacris.practicafinal.model.ArtistaModel;
import com.sofiaevacris.practicafinal.model.ProductoModel;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductoRepository extends CrudRepository<ProductoModel, Long> {


    @Query("SELECT * FROM PRODUCTOS WHERE PRODUCTOS.PRODUCTO_ID = :productoId")
    public List<ProductoModel> retrieveProducto(Long productoId);

}
