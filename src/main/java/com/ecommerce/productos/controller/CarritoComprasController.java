package com.ecommerce.productos.controller;


import com.ecommerce.productos.entity.CarritoCompras;
import com.ecommerce.productos.entity.Producto;
import com.ecommerce.productos.entity.Usuario;
import com.ecommerce.productos.service.CarritoComprasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrito")
public class CarritoComprasController {

    /*
    Los métodos addCarrito() y subtractCarrito() dejaron de funcionar en la web
     no sé si el error está en el front o en el back, buscar y arreglar.
    * */

    @Autowired
    private CarritoComprasService carritoComprasService;

    // Listar todos los carritos de todos los usuarios
    @GetMapping("/listar")
    public ResponseEntity<List<CarritoCompras>> getCarrito(){
        return new ResponseEntity<>(carritoComprasService.findAll(), HttpStatus.OK);
    }

    // Listar un carrito por el ID del usuario, solo muestra el carrito
    @GetMapping("/listar/{id}")
    public ResponseEntity<List<CarritoCompras>> getCarritoId(@PathVariable Long id){
        return new ResponseEntity<>(carritoComprasService.findByUsuarioId(id), HttpStatus.OK);
    }


    @PutMapping("/nuevo")
    public ResponseEntity<CarritoCompras> addCarrito(@RequestBody CarritoCompras carrito){
        return new ResponseEntity<>(carritoComprasService.addProducto(carrito), HttpStatus.OK);
    }

    @PutMapping("/restar")
    public ResponseEntity<CarritoCompras> subtractCarrito(@RequestBody CarritoCompras carrito){
        return new ResponseEntity<>(carritoComprasService.subtractProducto(carrito), HttpStatus.OK);
    }


    // Elimina toda la cantidad de un producto del carrito de un usuario
    // (al presionar el botón de 'X'. NO resta cantidad.)
    @DeleteMapping("/eliminar/producto/{id}")
    public ResponseEntity<Void> removeProductoId(@PathVariable Long id){
        carritoComprasService.deleteProductoById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Vacía el carrito de un usuario
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<List<CarritoCompras>> cleanCarritoId(@PathVariable Long id){
        return new ResponseEntity<>(carritoComprasService.deleteByUsuarioId(id), HttpStatus.OK);
    }

}
