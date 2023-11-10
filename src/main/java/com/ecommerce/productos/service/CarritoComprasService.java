package com.ecommerce.productos.service;

import com.ecommerce.productos.entity.CarritoCompras;
import com.ecommerce.productos.entity.Producto;
import com.ecommerce.productos.entity.Usuario;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarritoComprasService {

    List<CarritoCompras> findAll();

    List<CarritoCompras> findByUsuarioId(Long id);

    CarritoCompras findByUsuarioAndProducto(@Param("usuario") Usuario usuario,
                                                      @Param("producto") Producto producto);

    List<CarritoCompras> deleteByUsuarioId(Long idUsuario);

    Long countByUsuarioId(Integer id);

    void deleteProductoById(Long idProducto);

    CarritoCompras addProducto(CarritoCompras producto);

    CarritoCompras subtractProducto(CarritoCompras carrito);
}
