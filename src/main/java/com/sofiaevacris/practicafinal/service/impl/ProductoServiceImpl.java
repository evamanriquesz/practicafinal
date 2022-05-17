package com.sofiaevacris.practicafinal.service.impl;

import com.sofiaevacris.practicafinal.dto.ProductoDTO;
import com.sofiaevacris.practicafinal.dto.UsuarioDTO;
import com.sofiaevacris.practicafinal.model.ArtistaModel;
import com.sofiaevacris.practicafinal.model.ProductoModel;
import com.sofiaevacris.practicafinal.repository.ProductoRepository;
import com.sofiaevacris.practicafinal.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<ProductoDTO> retrieveAll() {
        return StreamSupport.stream(productoRepository.findAll().spliterator(), false)
                .map(obj -> new ProductoDTO(
                        obj.getProductoId(),
                        obj.getNombre(),
                        obj.getPrecio(),
                        obj.getExistencias()))
                .toList();
    }

    @Override
    public ProductoModel updateProducto(Long productoId, ProductoModel productoModel) {
        if(productoRepository.existsById(productoId))
        {
            return productoRepository.save(productoModel);
        }else{
            return null;
        }
    }


    @Override
    public ProductoModel retrieveProducto(Long productoId)
    {
        ProductoModel respuesta = null;
        if(productoRepository.existsById(productoId)){
            List<ProductoModel> productoModels = productoRepository.retrieveProducto(productoId);

            for(ProductoModel productoModel:productoModels)
            {
                respuesta = productoModel;
            }
        }
        return respuesta;
    }

}
