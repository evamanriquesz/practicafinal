package com.sofiaevacris.practicafinal.controller;

import com.sofiaevacris.practicafinal.dto.ProductoDTO;
import com.sofiaevacris.practicafinal.model.ArtistaModel;
import com.sofiaevacris.practicafinal.model.ProductoModel;
import com.sofiaevacris.practicafinal.repository.ProductoRepository;
import com.sofiaevacris.practicafinal.service.ProductoService;
import org.jetbrains.annotations.ApiStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/productos")
    public ResponseEntity<List<ProductoDTO>> retrieveAll()
    {
        return ResponseEntity.ok().body(productoService.retrieveAll());
    }

    @PutMapping("/productos/{productoId}")
    public ResponseEntity<ProductoModel> updateProducto(@PathVariable("productoId") Long productoId, @RequestBody ProductoModel productoModel){
        ProductoModel newProductoModel = productoService.updateProducto(productoId, productoModel);
        if(newProductoModel == null)
        {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok().body(newProductoModel);

    }


    @GetMapping("/productos/{productoId}")
    public ResponseEntity<ProductoModel> retrieveProducto(@PathVariable Long productoId){
        ProductoModel respuesta = productoService.retrieveProducto(productoId);
        return ResponseEntity.ok().body(respuesta);
    }



}
