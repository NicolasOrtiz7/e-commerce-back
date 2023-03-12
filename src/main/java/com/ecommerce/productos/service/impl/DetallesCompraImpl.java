package com.ecommerce.productos.service.impl;

import com.ecommerce.productos.entity.CarritoCompras;
import com.ecommerce.productos.entity.Compra;
import com.ecommerce.productos.entity.DetallesCompra;
import com.ecommerce.productos.repository.DetallesCompraRepository;
import com.ecommerce.productos.service.DetallesCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetallesCompraImpl implements DetallesCompraService {

    @Autowired
    private DetallesCompraRepository repository;


    @Override
    public Optional<DetallesCompra> detallesCompra(Integer id) {
        return repository.findByCompra(id);
    }

    @Override
    public List<DetallesCompra> getDetallesCompra() {
        return repository.findAll();
    }

    @Override
    public Optional<List<DetallesCompra>> getUsuarioCompras(Integer id) {
        return repository.findByCompraUsuario(id);
    }

    @Override
    public void saveDetallesCompra(DetallesCompra detalles) {
        repository.save(detalles);
    }

    @Override
    public List<DetallesCompra> findByCompraUsuarioId(Integer id){
        return repository.findByCompraUsuarioId(id);
    }

    public List<DetallesCompra> findByUserIdOrUsername(@Param("id") Long id,
                                               @Param("username") String username){
        return repository.findByUserIdOrUsername(id, username);
    }
}
