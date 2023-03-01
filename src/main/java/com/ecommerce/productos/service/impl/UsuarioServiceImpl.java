package com.ecommerce.productos.service.impl;

import com.ecommerce.productos.entity.*;
import com.ecommerce.productos.repository.UsuarioRepository;
import com.ecommerce.productos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public List<Usuario> buscarUsuarios() {
        return repository.findAll();
    }

    @Override
    public Optional<Usuario> buscarUsuarioId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void crearUsuario(Usuario usuario) {
        repository.save(usuario);
    }

    @Override
    public void editarUsuario(Usuario usuario) {
        repository.save(usuario);
    }

    @Override
    public void eliminarUsuario(Integer id) {
        repository.deleteById(id);
    }
}
