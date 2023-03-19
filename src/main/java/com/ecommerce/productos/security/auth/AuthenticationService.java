package com.ecommerce.productos.security.auth;

import com.ecommerce.productos.entity.Usuario;
import com.ecommerce.productos.repository.UsuarioRepository;
import com.ecommerce.productos.security.config.JwtService;
import com.ecommerce.productos.security.token.Token;
import com.ecommerce.productos.security.token.TokenRepository;
import com.ecommerce.productos.security.token.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UsuarioRepository repository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) {
    var user = Usuario.builder()
        .nombre(request.getNombre())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .rol(request.getRol())
        .build();
    var savedUser = repository.save(user);
    var jwtToken = jwtService.generateToken((UserDetails) user);
    saveUserToken(savedUser, jwtToken);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    var user = repository.findOneByEmail(request.getEmail())
        .orElseThrow();
    var jwtToken = jwtService.generateToken((UserDetails) user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .userId(user.getId()) // este lo cree yo para enviar la id del usuario actual
        .build();
  }

  private void saveUserToken(Usuario user, String jwtToken) {
    var token = Token.builder()
        .usuario(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(Usuario user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUsuario(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }
}
