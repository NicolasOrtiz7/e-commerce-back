package com.ecommerce.productos.repository;

import com.ecommerce.productos.entity.CarritoCompras;
import com.ecommerce.productos.entity.Producto;
import com.ecommerce.productos.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CarritoComprasRepository extends JpaRepository<CarritoCompras, Long> {

//    @Query("SELECT c FROM CarritoCompras c WHERE c.usuario.id_usuario = :id")
//    List<CarritoCompras> findByUsuarioId(Long id);

    @Query("SELECT c FROM CarritoCompras c WHERE c.usuario.id = :usuarioId")
    List<CarritoCompras> findByUsuarioId(Long usuarioId);

    // Elimina todos los productos del carrito de un usuario
    @Modifying
    @Query("DELETE FROM CarritoCompras c WHERE c.usuario.id = :idUsuario")
    void deleteByUsuarioId(@Param("idUsuario") Long idUsuario);

    // Elimina el producto en especifico
    @Modifying
    @Query("DELETE FROM CarritoCompras c WHERE c.id = :idProducto")
    void deleteProductoById(@Param("idProducto") Long idProducto);

    // Busca si un producto ya existe en el carrito de un usuario
    // Se usa para aumentar o restar cantidad
    @Query("SELECT c FROM CarritoCompras c WHERE c.usuario = :usuario AND c.productos = :producto")
    CarritoCompras findByUsuarioAndProducto(@Param("usuario") Usuario usuario,
                                                       @Param("producto") Producto producto);


}
