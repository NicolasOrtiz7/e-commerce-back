package com.ecommerce.productos.controller;


import com.ecommerce.productos.entity.Producto;
import com.ecommerce.productos.exception.UsuarioNotFound;
import com.ecommerce.productos.service.impl.ProductoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoServiceImpl productoService;

    /*

    CRUD DE PRODUCTOS
    1. Listar productos
    2. Buscar productos por id (barra busqueda)
    3. Buscar productos por categoria

     */

    @GetMapping("/listar")
    public List<Producto> findAll(){
        return productoService.findAllProductos();
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Producto> findById(@PathVariable Integer id){

        Optional<Producto> producto = productoService.findByProductoId(id);

        if (!producto.isPresent()){
            throw new UsuarioNotFound("El producto con id: " + id + " no existe.");
        }
        return new ResponseEntity(producto, HttpStatus.OK);
    }

    @GetMapping("/categoria")
    public List<Producto> getProductosByCategoria(
            @RequestParam("categoria") String nombreCategoria,
            @RequestParam(required = false, defaultValue = "ASC") String orden) {

        switch (orden){
            case "ASC": return productoService.findByCategoriaNombreOrderByNombreAsc(nombreCategoria);
            case "DESC": return productoService.findByCategoriaNombreOrderByNombreDesc(nombreCategoria);
            case "BARATO": return productoService.findByCategoriaNombreOrderByPrecioAsc(nombreCategoria);
            case "CARO": return productoService.findByCategoriaNombreOrderByPrecioDesc(nombreCategoria);
        }

        return productoService.findByCategoriaNombre(nombreCategoria);
    }


    @PostMapping("/nuevo")
    public ResponseEntity<Producto> create(@RequestBody Producto producto){
        productoService.saveProducto(producto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Producto> update(@RequestBody Producto producto, @PathVariable Integer id){

        Optional<Producto> producto1 = productoService.findByProductoId(id);

        if (!producto1.isPresent()){
            throw new UsuarioNotFound("No existe el Producto con id: " + id);
        }

        Producto productoNuevo = producto1.get();
        productoNuevo.setId(producto1.get().getId());

        productoNuevo.setNombre(producto.getNombre());
        productoNuevo.setDescripcion(producto.getDescripcion());
        productoNuevo.setImagen(producto.getImagen());
        productoNuevo.setCantidad(producto.getCantidad());
        productoNuevo.setPrecio(producto.getPrecio());
        productoNuevo.setCategoria(producto.getCategoria());

        productoService.updateProducto(productoNuevo);

        return new ResponseEntity(HttpStatus.OK);
    }



    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id){
        productoService.deleteProducto(id);
    }


}
