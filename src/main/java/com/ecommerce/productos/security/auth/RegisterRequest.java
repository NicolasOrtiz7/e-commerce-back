package com.ecommerce.productos.security.auth;

import com.ecommerce.productos.entity.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  private String nombre;
  private String email;
  private String password;
  private Rol rol;
}
