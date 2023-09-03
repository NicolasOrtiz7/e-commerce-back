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
    1. Sin autorización
        - Listar todos
        - Listar por ID
        - Listar por nombre
        - Listar por categoría

    2. Requiere autorización (implementar)
        - Crear nuevo
        - Editar producto
        - Eliminar producto

     */

    @GetMapping("/listar")
    public List<Producto> findAll(){
        return productoService.findAllProductos();
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity findById(@PathVariable Integer id){

        Optional<Producto> producto = productoService.findByProductoId(id);

        if (producto.isEmpty()){
            throw new UsuarioNotFound("El producto con id: " + id + " no existe.");
        }
        return new ResponseEntity(producto, HttpStatus.OK);
    }

    @GetMapping("/filtrar")
    public List<Producto> findByKeyword(@RequestParam("search") String keyword){
        return productoService.findByNombreContaining(keyword);
    }

    // Devuelve todos los productos
    @GetMapping("/todos")
    public List<Producto> getProductos(){
        return productoService.findAllProductos();
    }

    // Devuelve los productos filtrados por categoría
    @GetMapping("/categoria")
    public List<Producto> getProductosByCategoria(
            @RequestParam("categoria") String nombreCategoria,
            @RequestParam(required = false, defaultValue = "ASC") String orden) {

        return switch (orden) {
            case "ASC" -> productoService.findByCategoriaNombreOrderByNombreAsc(nombreCategoria);
            case "DESC" -> productoService.findByCategoriaNombreOrderByNombreDesc(nombreCategoria);
            case "BARATO" -> productoService.findByCategoriaNombreOrderByPrecioAsc(nombreCategoria);
            case "CARO" -> productoService.findByCategoriaNombreOrderByPrecioDesc(nombreCategoria);
            default -> productoService.findByCategoriaNombre(nombreCategoria);
        };

    }


    @PostMapping("/admin/nuevo")
    public ResponseEntity create(@RequestBody Producto producto){
        productoService.saveProducto(producto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/admin/edit/{id}")
    public ResponseEntity update(@RequestBody Producto producto, @PathVariable Integer id){

        Optional<Producto> producto1 = productoService.findByProductoId(id);

        if (producto1.isEmpty()){
            throw new UsuarioNotFound("No existe el Producto con id: " + id);
        }

        Producto productoNuevo = producto1.get();
        productoNuevo.setId(producto1.get().getId());

        productoNuevo.setNombre(producto.getNombre());
        productoNuevo.setDescripcion(producto.getDescripcion());
        productoNuevo.setImagen(producto.getImagen());
        productoNuevo.setPrecio(producto.getPrecio());
        productoNuevo.setCategoria(producto.getCategoria());

        productoService.updateProducto(productoNuevo);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/admin/delete/{id}")
    public void delete(@PathVariable Integer id){
        productoService.deleteProducto(id);
    }


}
