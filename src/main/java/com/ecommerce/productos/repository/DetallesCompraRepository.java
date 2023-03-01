package com.ecommerce.productos.repository;

import com.ecommerce.productos.entity.DetallesCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DetallesCompraRepository extends JpaRepository<DetallesCompra, Integer> {

    Optional<DetallesCompra> findByCompra(Integer id);

}