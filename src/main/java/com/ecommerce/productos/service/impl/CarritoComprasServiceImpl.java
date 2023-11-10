package com.ecommerce.productos.service.impl;

import com.ecommerce.productos.entity.CarritoCompras;
import com.ecommerce.productos.entity.Producto;
import com.ecommerce.productos.entity.Usuario;
import com.ecommerce.productos.exception.CarritoNotFound;
import com.ecommerce.productos.repository.CarritoComprasRepository;
import com.ecommerce.productos.service.CarritoComprasService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class CarritoComprasServiceImpl implements CarritoComprasService {

    @Autowired
    private CarritoComprasRepository repository;

    @Override
    public List<CarritoCompras> findAll() {
        return repository.findAll();
    }

    @Override
    public List<CarritoCompras> findByUsuarioId(Long id) {
        return repository.findByUsuarioId(id);
    }

    @Override
    public CarritoCompras findByUsuarioAndProducto(Usuario usuario, Producto producto) {
        return repository.findByUsuarioAndProducto(usuario, producto);
    }

    @Override
    public CarritoCompras addProducto(CarritoCompras carrito) {

        Usuario usuario = carrito.getUsuario();
        Producto producto = carrito.getProductos();

        // Busca si el producto ya existe en el carrito de un usuario
        CarritoCompras carritoCompras = findByUsuarioAndProducto(usuario, producto);

        // Si el producto no existe en el carrito, se crea y se agrega 1
        if (carritoCompras == null) {
            carritoCompras = new CarritoCompras();
            carritoCompras.setUsuario(usuario);
            carritoCompras.setProductos(producto);
            carritoCompras.setCantidad(1);
        }
        // Si el producto ya existe en el carrito, se suma 1
        else {
            int nuevaCantidad = carritoCompras.getCantidad() + 1;
            carritoCompras.setCantidad(nuevaCantidad);
        }

        return repository.save(carritoCompras);
    }

    @Override
    public CarritoCompras subtractProducto(CarritoCompras carrito){

        // Busca si el producto ya existe en el carrito de un usuario
        CarritoCompras carritoCompras = findByUsuarioAndProducto(
                carrito.getUsuario(), carrito.getProductos());

        // Si existe y la cantidad es mayor que 1, le resta 1. Si la cantidad es menos de 1, lo elimina
        if (carritoCompras != null){
            if (carritoCompras.getCantidad() > 1) {
                carritoCompras.setCantidad(carrito.getCantidad() - 1);
//                return addProducto(carritoCompras);
                return repository.save(carritoCompras);
            } else{
                 deleteProductoById(carrito.getId());
                 return carrito;
            }
        }
        throw new CarritoNotFound("No se encontró el producto en el carrito del usuario");
    }

    @Override
    public List<CarritoCompras> deleteByUsuarioId(Long idUsuario) {
        List<CarritoCompras> carrito = repository.findByUsuarioId(idUsuario);
        repository.deleteByUsuarioId(idUsuario);
        return carrito;
    }

    @Override
    public Long countByUsuarioId(Integer id) {
        return null;
    }

    @Override
    public void deleteProductoById(Long idProducto) {
        repository.deleteProductoById(idProducto);
    }



}
