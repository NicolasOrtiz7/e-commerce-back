package com.ecommerce.productos.service;

import com.ecommerce.productos.entity.CarritoCompras;
import com.ecommerce.productos.entity.Compra;

import java.util.List;
import java.util.Optional;

public interface CompraService {

    public List<Compra> findCompras();

    public List<Compra> findUsuarioCompras(Integer id);

    public void saveCompra(Compra compra);

}
