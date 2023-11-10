package com.ecommerce.productos.controller;


import com.ecommerce.productos.entity.Categoria;
import com.ecommerce.productos.exception.UsuarioNotFound;
import com.ecommerce.productos.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*
    CRUD DE CATEGORIAS
    1. Sin autorización
        - Listar todos
        - Listar por ID

    2. Requiere autorización (implementar)
        - Crear nueva
        - Editar categoría
        - Eliminar categoría
     */

@RestController
@RequestMapping("/categorias")
public class CategoriaController {


    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/listar")
    public ResponseEntity<List<Categoria>> getCategorias(){
        return new ResponseEntity<>(categoriaService.findAllCategorias(), HttpStatus.OK);
    }
    @GetMapping("/listar/{id}")
    public ResponseEntity<Optional<Categoria>> getCategorias(@PathVariable Integer id){
        return new ResponseEntity<>(categoriaService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/admin/nuevo")
    public ResponseEntity<Categoria> saveCategoria(@RequestBody Categoria categoria){
        return new ResponseEntity<>(categoriaService.saveCategoria(categoria), HttpStatus.CREATED);
    }

    @PutMapping("/admin/editar/{id}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable Integer id, @RequestBody Categoria categoria){
        return new ResponseEntity<>(categoriaService.updateCategoria(id, categoria), HttpStatus.OK);
    }


    @DeleteMapping("/admin/eliminar/{id}")
    public ResponseEntity<Categoria> deleteCategoria(@PathVariable Integer id){
        return new ResponseEntity<>(categoriaService.deleteCategoria(id), HttpStatus.OK);
    }

}
