package com.ecommerce.productos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String username;
    private String email;
    private String direccion;
    private String telefono;
    private String password;

    @OneToOne()
    @JoinColumn(name = "rol_id")
    private Rol rol;


    /* @OneToMany(mappedBy = "usuario")
    private List<Producto> productos;  ESTA YA NO SIRVE, NO DESCOMENTAR*/

   /* @OneToMany(mappedBy = "usuario")
    private List<Compra> compras; // cambiar nombre a "ordenes"*/

}
