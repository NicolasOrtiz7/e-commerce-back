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
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String descripcion;
    private String imagen = "no-image.png";
    private double precio;

    /* @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario; */

    @OneToOne()
    @JoinColumn(name = "categoria_id")
    Categoria categoria;
}
