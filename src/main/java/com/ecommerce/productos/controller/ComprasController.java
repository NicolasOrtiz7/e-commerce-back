package com.ecommerce.productos.controller;

import com.ecommerce.productos.entity.CarritoCompras;
import com.ecommerce.productos.entity.Compra;
import com.ecommerce.productos.entity.DetallesCompra;
import com.ecommerce.productos.service.CompraService;
import com.ecommerce.productos.service.DetallesCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compras")
public class ComprasController {

    /*

    Compras
    1. Sin autorización
        - Ver mis propias compras (implementar)

    2. Requiere autorización (implementar)
        - Todos los métodos

     */

    /*

    ESTE CONTROLLER LO PUEDO HACER EN EL PRODUCTO CONTROLLER


    1. Ver todas las compras
    2. Ver compras de un usuario (este lo hago en el usuario controller creo)

     */

    @Autowired
    private DetallesCompraService detallesCompraService;
    @Autowired
    private CompraService compraService;

    @GetMapping("/listar")
    public List<DetallesCompra> getCompras(){
        return detallesCompraService.getDetallesCompra();
    }

    @GetMapping("/listar/{id}")
    public List<DetallesCompra> getUsuarioCompras(@PathVariable Integer id){
        // Arreglar este metodo. CREO que este ya no lo uso (si lo uso, ver si conviene eliminar)
        return detallesCompraService.findByCompraUsuarioId(id);
    }

    // Filtrar enviando id o username
    @GetMapping("/filtrar")
    public List<DetallesCompra> getUsuarioComprass(@Param("keyword") String keyword){
        return detallesCompraService.findByUserIdOrUsername(keyword);
    }

    @PostMapping("/save")
    public void saveCompra(@RequestBody CarritoCompras[] carritoCompras){

        // Cada vez que se hace una compra, debería guardarse solo una fila en la bbdd,
        // pero se guarda una por cada producto, corregir

        for(CarritoCompras a:carritoCompras){
            DetallesCompra detalles = new DetallesCompra();
            Compra compra = new Compra();

            detalles.setCantidad(a.getCantidad());
            detalles.setProducto(a.getProductos());

            compra.setUsuario(a.getUsuario());
            detalles.setCompra(compra);

            compraService.saveCompra(compra);
            detallesCompraService.saveDetallesCompra(detalles);
        }
    }



}
