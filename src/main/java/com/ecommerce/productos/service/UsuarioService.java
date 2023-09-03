package com.ecommerce.productos.service;

import com.ecommerce.productos.entity.*;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> findUsuarios();

    Optional<Usuario> findUsuarioById(Integer id);

    void saveUsuario(Usuario usuario);

    void updateUsuario(Usuario usuario);

    void deleteUsuario(Integer id);


}
