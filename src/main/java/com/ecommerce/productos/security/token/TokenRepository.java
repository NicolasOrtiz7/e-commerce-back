package com.ecommerce.productos.security.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

  @Query(value = "select t from Token t inner join Usuario u on t.usuario.id = u.id where u.id = :id and (t.expired = false or t.revoked = false)")
  List<Token> findAllValidTokenByUsuario(Integer id);

  Optional<Token> findByToken(String token);
}
