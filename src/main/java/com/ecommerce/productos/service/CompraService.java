package com.ecommerce.productos.service;

import com.ecommerce.productos.entity.Compra;

import java.util.List;

public interface CompraService {

    List<Compra> findCompras();

    List<Compra> findUsuarioCompras(Integer id);

    void saveCompra(Compra compra);

}
