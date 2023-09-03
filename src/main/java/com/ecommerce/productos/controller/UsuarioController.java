package com.ecommerce.productos.controller;


import com.ecommerce.productos.entity.Compra;
import com.ecommerce.productos.entity.Rol;
import com.ecommerce.productos.entity.Usuario;
import com.ecommerce.productos.exception.UsuarioNotFound;
import com.ecommerce.productos.service.impl.CompraServiceImpl;
import com.ecommerce.productos.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioService;
    @Autowired
    private CompraServiceImpl compraService;

    /*

    CRUD DE USUARIOS
    1. Sin autorización
        - Editar contraseña y datos

    2. Requiere autorización (implementar)
        - Eliminar

    3. Verificar
        -

     */


    /*

    1. CRUD. listar usuarios --
    2. Poder asignar un rol desde el crud
    3. Poder ver sus compras
    4. Cambiar contraseña

    */

    /*

    Testeados
    1. listar
    2. nuevo (bien, el rol se puede enviar solo con id y sin nombre)
    3. list id
    4. delete
    5. edit/id

    Modificar
    - id/password
    - id/rol

    Testear
    - compras/id  --> en ComprasRepository, recibe un id, pero deberia recibir un usuario.id


    */

    @GetMapping("/admin/listar")
    public List<Usuario> findAll(){
        return usuarioService.findUsuarios();
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity findById(@PathVariable Integer id){

        Optional<Usuario> usuario = usuarioService.findUsuarioById(id);

        if (usuario.isEmpty()){
            throw new UsuarioNotFound("El alumno con id: " + id + " no existe.");
        }
        return new ResponseEntity(usuario, HttpStatus.OK);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario){

        Rol usuarioRol = new Rol();
        usuarioRol.setId(2);
        usuarioRol.setNombre("user");

        // Valores por defecto al crear nuevo usuario (despues se puede modificar)
        usuario.setRol(usuarioRol);
       // usuario.setCompras(null);

        usuarioService.saveUsuario(usuario);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Usuario> update(@RequestBody Usuario usuario, @PathVariable Integer id){

        Optional<Usuario> usuario1 = usuarioService.findUsuarioById(id);

        if (usuario1.isEmpty()){
            throw new UsuarioNotFound("No existe el usuario con id: " + id);
        }
        Usuario usuarioNuevo = new Usuario();

        // Atributos que no se pueden cambiar
        usuarioNuevo.setId(usuario1.get().getId());
        usuarioNuevo.setNombre(usuario.getNombre());
        usuarioNuevo.setUsername(usuario.getUsername());
       // usuarioNuevo.setCompras(usuario.getCompras());
        usuarioNuevo.setPassword(usuario.getPassword()); // La pass se cambia desde otro lado
        usuarioNuevo.setRol(usuario.getRol()); // El rol se cambia desde otro lado

        // Atributos modificables
        usuarioNuevo.setEmail(usuario.getEmail());
        usuarioNuevo.setDireccion(usuario.getDireccion());
        usuarioNuevo.setTelefono(usuario.getTelefono());

        usuarioService.updateUsuario(usuarioNuevo);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/admin/delete/{id}")
    public void delete(@PathVariable Integer id){
        usuarioService.deleteUsuario(id);
    }

    @GetMapping("/{id}/compras")
    public List<Compra> findUsuarioCompras(@PathVariable Integer id){
        return compraService.findUsuarioCompras(id);
    }

   /* @GetMapping("/{id}/compras")
    public List<Compra> findUsuarioCompras(@PathVariable Integer id){
        return usuarioService.buscarUsuarioId(id).get().getCompras();
    } */


    // Funciona pero no es necesario enviar el usuario completo, enviar solo el rol
    // podria ser peligroso porque se puede cambiar cualquier atributo desde la peticion
    @PutMapping("/admin/{id}/rol")
    public ResponseEntity<Usuario> changeRol(@PathVariable Integer id, @RequestBody Usuario usuario){
        Optional<Usuario> usuario1 = usuarioService.findUsuarioById(id);
        if (usuario1.isEmpty()){
            throw new UsuarioNotFound("No existe un usuario con id: " + usuario.getId());
        }
        usuario.setRol(usuario.getRol());
        usuarioService.updateUsuario(usuario);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // Funciona pero no es necesario enviar el usuario completo, enviar solo la password
    // podria ser peligroso porque se puede cambiar cualquier atributo desde la peticion
    @PutMapping("/{id}/password")
    public ResponseEntity<Usuario> changePassword(@PathVariable Integer id, @RequestBody Usuario usuario){
        Optional<Usuario> usuario1 = usuarioService.findUsuarioById(usuario.getId());
        if (usuario1.isEmpty()){
            throw new UsuarioNotFound("No existe un usuario con id: " + usuario.getId());
        }
        usuario1.get().setPassword(usuario.getPassword());
        usuarioService.updateUsuario(usuario1.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
