package com.ecommerce.productos.service.impl;

import com.ecommerce.productos.entity.Rol;
import com.ecommerce.productos.repository.RolRepository;
import com.ecommerce.productos.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository repository;

    @Override
    public List<Rol> findRoles() {
        return repository.findAll();
    }
}
