package com.ecommerce.productos.repository;

import com.ecommerce.productos.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByUsername(String username);

    Optional<Usuario> findOneByEmail(String email);

}
