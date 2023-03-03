package com.ecommerce.productos.controller;


import com.ecommerce.productos.entity.CarritoCompras;
import com.ecommerce.productos.entity.Producto;
import com.ecommerce.productos.entity.Usuario;
import com.ecommerce.productos.service.CarritoComprasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public void addCarrito(@RequestBody CarritoCompras carrito){

        Usuario usuario = carrito.getUsuario();
        Producto producto = carrito.getProductos();

        // Busca si el producto ya existe en el carrito de un usuario
        CarritoCompras carritoCompras = carritoComprasService
                .findByUsuarioAndProducto(usuario, producto);

        // Si el producto no existe en el carrito, se crea y se agrega 1
        if (carritoCompras == null) {
            carritoCompras = new CarritoCompras();
            carritoCompras.setUsuario(usuario);
            carritoCompras.setProductos(producto);
            carritoCompras.setCantidad(1);
        }
        // Si el producto ya existe en el carrito, se suma 1
        else {
            int nuevaCantidad = carritoCompras.getCantidad() + 1;
            carritoCompras.setCantidad(nuevaCantidad);
        }

        carritoComprasService.addProducto(carritoCompras);
    }
    @PostMapping("/restar")
    public void subtractCarrito(@RequestBody CarritoCompras carrito){

        Usuario usuario = carrito.getUsuario();
        Producto producto = carrito.getProductos();

        // Busca si el producto ya existe en el carrito de un usuario
        CarritoCompras carritoCompras = carritoComprasService
                .findByUsuarioAndProducto(usuario, producto);

        // Si existe y la cantidad es mayor que 1, le resta 1. Si la cantidad de menos de 1, lo elimina
        if (carritoCompras != null){
            if (carritoCompras.getCantidad() > 1) {
                carritoCompras.setCantidad(carrito.getCantidad() - 1);
                carritoComprasService.addProducto(carritoCompras);
            } else{
                carritoComprasService.deleteProductoById(Long.valueOf(carrito.getId()));
            }

        }
    }


    // Elimina toda la cantidad de un producto del carrito de un usuario
    // (al presionar el botón de 'X'. NO resta cantidad.)
    @DeleteMapping("/eliminar/producto/{id}")
    public void removeProductoId(@PathVariable Long id){
        carritoComprasService.deleteProductoById(id);
    }

    // Vacía el carrito de un usuario
    @DeleteMapping("/eliminar/{id}")
    public void cleanCarritoId(@PathVariable Long id){
        carritoComprasService.deleteByUsuarioId(id);
    }

}
