package com.ecommerce.productos.security;

import com.ecommerce.productos.entity.Usuario;
import com.ecommerce.productos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository
                .findOneByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No existe el email: " + email));

        return new UserDetailsImpl(usuario);
    }
}
