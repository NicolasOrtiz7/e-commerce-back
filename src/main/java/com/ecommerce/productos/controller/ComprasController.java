package com.ecommerce.productos.controller;

import com.ecommerce.productos.entity.CarritoCompras;
import com.ecommerce.productos.entity.Compra;
import com.ecommerce.productos.entity.DetallesCompra;
import com.ecommerce.productos.service.CompraService;
import com.ecommerce.productos.service.DetallesCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/compras")
public class ComprasController {

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
        List<DetallesCompra> compras = detallesCompraService.getDetallesCompra();
        return compras;
    }

    @GetMapping("/listar/{id}")
    public List<DetallesCompra> getUsuarioCompras(){
        List<DetallesCompra> compras = detallesCompraService.getDetallesCompra();
        return compras;
    }

    @PostMapping("/save")
    public void saveCompra(@RequestBody CarritoCompras[] carritoCompras){



        for(CarritoCompras a:carritoCompras){

            DetallesCompra detalles = new DetallesCompra();
            Compra compra = new Compra();

            System.out.println(a);

            detalles.setCantidad(a.getCantidad());
            detalles.setProducto(a.getProductos());

            compra.setUsuario(a.getUsuario());
            detalles.setCompra(compra);


            compraService.saveCompra(compra);
            detallesCompraService.saveDetallesCompra(detalles);

        }


        /*detalles.setCantidad(carritoCompras.getCantidad());
        detalles.setProducto(carritoCompras.getProductos());

        compra.setUsuario(carritoCompras.getUsuario());*/

      /*  detalles.setCompra(compra);*/
/*
        compraService.saveCompra(compra);
        detallesCompraService.saveDetallesCompra(detalles);*/
    }


}
