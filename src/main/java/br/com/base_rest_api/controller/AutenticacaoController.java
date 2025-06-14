package br.com.base_rest_api.controller;

import br.com.base_rest_api.domain.dto.request.autenticacao.LoginRequest;
import br.com.base_rest_api.domain.dto.request.autenticacao.RefreshTokenRequest;
import br.com.base_rest_api.domain.dto.request.autenticacao.UsuarioRequest;
import br.com.base_rest_api.domain.dto.response.autenticacao.LoginResponse;
import br.com.base_rest_api.domain.dto.response.autenticacao.UsuarioResponse;
import br.com.base_rest_api.service.AutenticacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AutenticacaoController {

    private final AutenticacaoService autenticacaoService;

    @PostMapping("/cadastre-se")
    public ResponseEntity<UsuarioResponse> cadastrarUsuario(@RequestBody @Valid UsuarioRequest usuarioRequest) {
        UsuarioResponse usuarioResponse = this.autenticacaoService.criarUsuario(usuarioRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioResponse);
    }

    @PostMapping("/entrar")
    public ResponseEntity<LoginResponse> autenticarUsuario(@RequestBody @Valid LoginRequest loginRequest) {
        return ResponseEntity.ok(this.autenticacaoService.autenticarUsuario(loginRequest));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<LoginResponse> renovarTokens(@RequestBody @Valid RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(this.autenticacaoService.renovarTokens(refreshTokenRequest.refreshToken()));
    }

}