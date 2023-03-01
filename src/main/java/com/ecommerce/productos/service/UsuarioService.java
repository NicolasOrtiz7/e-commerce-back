package com.ecommerce.productos.service;

import com.ecommerce.productos.entity.*;
import com.ecommerce.productos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    public List<Usuario> buscarUsuarios();

    public Optional<Usuario> buscarUsuarioId(Integer id);

    public void crearUsuario(Usuario usuario);

    public void editarUsuario(Usuario usuario);

    public void eliminarUsuario(Integer id);


}
