package com.ecommerce.productos.repository;

import com.ecommerce.productos.entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Integer> {

    List<Compra> findAllByUsuario(Integer id);

}
