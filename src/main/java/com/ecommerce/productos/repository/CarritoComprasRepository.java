package com.ecommerce.productos.repository;

import com.ecommerce.productos.entity.CarritoCompras;
import com.ecommerce.productos.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarritoComprasRepository extends JpaRepository<CarritoCompras, Long> {

//    @Query("SELECT c FROM CarritoCompras c WHERE c.usuario.id_usuario = :id")
//    List<CarritoCompras> findByUsuarioId(Long id);

    @Query("SELECT c FROM CarritoCompras c WHERE c.usuario.id = :usuarioId")
    List<CarritoCompras> findByUsuarioId(Long usuarioId);

    // Este elimina todos los productos del carrito de un usuario
    @Modifying
    @Query("DELETE FROM CarritoCompras c WHERE c.usuario.id = :idUsuario")
    void deleteByUsuarioId(@Param("idUsuario") Long idUsuario);

    // Este es el que elimina el producto en especifico
    @Modifying
    @Query("DELETE FROM CarritoCompras c WHERE c.id = :idProducto")
    void deleteProductoById(@Param("idProducto") Long idProducto);

}
