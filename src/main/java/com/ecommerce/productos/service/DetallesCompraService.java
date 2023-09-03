package com.ecommerce.productos.service;

import com.ecommerce.productos.entity.DetallesCompra;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DetallesCompraService {

    Optional<DetallesCompra> detallesCompra(Integer id);

    List<DetallesCompra> getDetallesCompra();

    Optional<List<DetallesCompra>> getUsuarioCompras(Integer id);

    void saveDetallesCompra(DetallesCompra detalles);

    List<DetallesCompra> findByCompraUsuarioId(Integer id);

    List<DetallesCompra> findByUserIdOrUsername(@Param("keyword") String keyword );

}
