package com.ecommerce.productos.repository;

import com.ecommerce.productos.entity.Categoria;
import com.ecommerce.productos.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    List<Categoria> findByNombre(String name);

}
