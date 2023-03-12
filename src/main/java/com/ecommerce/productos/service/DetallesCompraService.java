package com.ecommerce.productos.service;

import com.ecommerce.productos.entity.CarritoCompras;
import com.ecommerce.productos.entity.Compra;
import com.ecommerce.productos.entity.DetallesCompra;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DetallesCompraService {

    public Optional<DetallesCompra> detallesCompra(Integer id);

    public List<DetallesCompra> getDetallesCompra();

    public Optional<List<DetallesCompra>> getUsuarioCompras(Integer id);

    public void saveDetallesCompra(DetallesCompra detalles);

    public List<DetallesCompra> findByCompraUsuarioId(Integer id);

    public List<DetallesCompra> findByUserIdOrUsername(@Param("id") Long id, @Param("username") String username);

}
