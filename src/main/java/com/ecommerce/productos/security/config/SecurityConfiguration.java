package com.ecommerce.productos.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;
  private final LogoutHandler logoutHandler;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .cors().and().csrf().disable()

            .authorizeHttpRequests()
            .requestMatchers("/api/v1/auth/**").permitAll()

            .requestMatchers("/productos/admin/**").hasRole("admin")
            .requestMatchers("/productos/**").permitAll()

            .requestMatchers("/compras/mis-compras").authenticated()
            .requestMatchers("/compras/**").hasRole("admin")

            .requestMatchers("/categorias/admin/**").hasRole("admin")
            .requestMatchers("/categorias/**").permitAll()

            .requestMatchers("/carrito/**").permitAll()

            .requestMatchers("/usuarios/admin/**").hasRole("admin")
            .requestMatchers("/usuarios/**").permitAll()

            .anyRequest().authenticated()

        .and()
          .sessionManagement()
          .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .logout()
        .logoutUrl("/api/v1/auth/logout")
        .addLogoutHandler(logoutHandler)
        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
    ;

    return http.build();
  }
}
