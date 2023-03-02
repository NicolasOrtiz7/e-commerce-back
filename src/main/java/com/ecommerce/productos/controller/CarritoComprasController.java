package com.ecommerce.productos.controller;


import com.ecommerce.productos.entity.CarritoCompras;
import com.ecommerce.productos.service.CarritoComprasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrito")
public class CarritoComprasController {

    @Autowired
    private CarritoComprasService carritoComprasService;

    // Listar todos los carritos de todos los usuarios
    @GetMapping("/listar")
    public List<CarritoCompras> getCarrito(){
        return carritoComprasService.findAll();
    }

    // Listar un carrito por el id del usuario, solo muestra el carrito
    @GetMapping("/listar/{id}")
    public List<CarritoCompras> getCarritoId(@PathVariable Long id){
        return carritoComprasService.findByUsuarioId(id);
    }

    @PostMapping("/nuevo")
    public void addCarrito(@RequestBody CarritoCompras producto){
        carritoComprasService.addProducto(producto);
    }

    // ESTE elimna todos los productos del carrito del usuario (cambiar, esta mal)
    @DeleteMapping("/eliminar/{id}")
    public void deleteCarritoId(@PathVariable Long id){
        carritoComprasService.deleteByUsuarioId(id);
    }

    // ESTE elimina un producto en especifico de cada carrito de un usuario
    @DeleteMapping("/eliminar/producto/{id}")
    public void deleteProductoId(@PathVariable Long id){
        carritoComprasService.deleteProductoById(id);
    }



}
