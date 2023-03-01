package com.ecommerce.productos.repository;

import com.ecommerce.productos.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query("SELECT p FROM Producto p WHERE p.categoria.nombre = :nombreCategoria")
    List<Producto> findByCategoriaNombre(String nombreCategoria);

//    @Query("SELECT p FROM Producto p WHERE p.categoria.nombre = :nombreCategoria ORDER BY p.nombre")
    List<Producto> findByCategoriaNombreOrderByNombreAsc(String nombreCategoria);

//    @Query("SELECT p FROM Producto p WHERE p.categoria.nombre = :nombreCategoria ORDER BY p.nombre DESC")
    List<Producto> findByCategoriaNombreOrderByNombreDesc(String nombreCategoria);


    List<Producto> findByCategoriaNombreOrderByPrecioAsc(String nombreCategoria);


    List<Producto> findByCategoriaNombreOrderByPrecioDesc(String nombreCategoria);


}
