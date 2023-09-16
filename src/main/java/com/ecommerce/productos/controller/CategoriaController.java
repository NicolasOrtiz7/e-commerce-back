package com.ecommerce.productos.controller;


import com.ecommerce.productos.entity.Categoria;
import com.ecommerce.productos.exception.UsuarioNotFound;
import com.ecommerce.productos.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Categoria> getCategorias(){
        return categoriaService.findAllCategorias();
    }
    @GetMapping("/listar/{id}")
    public Optional<Categoria> getCategorias(@PathVariable Integer id){

        return categoriaService.findById(id);
    }

    @PostMapping("/admin/nuevo")
    public void saveCategoria(@RequestBody Categoria categoria){
        categoriaService.saveCategoria(categoria);
    }

    @PutMapping("/admin/editar/{id}")
    public void updateCategoria(@PathVariable Integer id, @RequestBody Categoria categoria){

        Optional<Categoria> categoriaExists = categoriaService.findById(id);

        if (categoriaExists.isEmpty()){
            throw new UsuarioNotFound("No existe esa categoría"); // cambiar el throw
        }
        categoria.setId(id);

        categoriaService.saveCategoria(categoria);
    }


    @DeleteMapping("/admin/eliminar/{id}")
    public void deleteCategoria(@PathVariable Integer id){
        categoriaService.deleteCategoria(id);
    }

}
