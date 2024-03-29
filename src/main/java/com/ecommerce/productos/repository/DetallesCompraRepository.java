package com.ecommerce.productos.repository;

import com.ecommerce.productos.entity.Compra;
import com.ecommerce.productos.entity.DetallesCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DetallesCompraRepository extends JpaRepository<DetallesCompra, Integer> {

    Optional<DetallesCompra> findByCompra(Integer id);

    Optional<List<DetallesCompra>> findByCompraUsuario(Integer id);

    List<DetallesCompra> findByCompraUsuarioId(Integer id);

    @Query("SELECT dc FROM DetallesCompra dc JOIN dc.compra c WHERE c.usuario.username = :keyword OR c.usuario.id = :keyword")
    List<DetallesCompra> findByUserIdOrUsername(@Param("keyword") String keyword);


}
