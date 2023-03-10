package com.ecommerce.productos.service;

import com.ecommerce.productos.entity.CarritoCompras;
import com.ecommerce.productos.entity.Compra;

import java.util.List;
import java.util.Optional;

public interface CompraService {

    public List<Compra> buscarCompras();

    public List<Compra> buscarUsuarioCompras(Integer id);

    public void saveCompra(Compra compra);

}
