package br.com.base_rest_api.service;

import br.com.base_rest_api.config.JWTService;
import br.com.base_rest_api.domain.dto.request.autenticacao.LoginRequest;
import br.com.base_rest_api.domain.dto.request.autenticacao.UsuarioRequest;
import br.com.base_rest_api.domain.dto.response.autenticacao.LoginResponse;
import br.com.base_rest_api.domain.dto.response.autenticacao.UsuarioResponse;
import br.com.base_rest_api.domain.entity.Usuario;
import br.com.base_rest_api.exception.RegraNegocioException;
import br.com.base_rest_api.mapper.UsuarioMapper;
import br.com.base_rest_api.repository.usuario.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AutenticacaoService {

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final JWTService jwtService;

    private final UsuarioMapper usuarioMapper;

    @Transactional
    public UsuarioResponse criarUsuario(UsuarioRequest usuarioRequest) {
        Usuario novoUsuario = new Usuario();
        novoUsuario.setCpf(usuarioRequest.cpf());
        novoUsuario.setSenha(this.passwordEncoder.encode(usuarioRequest.senha()));
        novoUsuario.setNomeCompleto(usuarioRequest.nomeCompleto());
        novoUsuario = this.usuarioRepository.save(novoUsuario);
        return this.usuarioMapper.converterParaUsuarioResponse(novoUsuario);
    }

    public LoginResponse autenticarUsuario(LoginRequest loginRequest) {
        try {
            Authentication authentication = this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.cpf(),
                            loginRequest.senha()
                    ));

            Usuario usuarioAutenticado = (Usuario) authentication.getPrincipal();

            return new LoginResponse(
                    this.jwtService.generateAccessToken(usuarioAutenticado),
                    this.jwtService.generateRefreshToken(usuarioAutenticado),
                    this.jwtService.getJwtAccessTokenExpiration(),
                    this.jwtService.getJwtRefreshTokenExpiration(),
                    this.usuarioMapper.converterParaUsuarioResponse(usuarioAutenticado)
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Credenciais inválidas");
        }
    }

    public LoginResponse renovarTokens(String refreshToken) {
        try {
            String username = this.jwtService.extractUsername(refreshToken);
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            if (!this.jwtService.isTokenValid(refreshToken, userDetails)) {
                throw new RegraNegocioException("Refresh Token inválido ou expirado.");
            }

            Usuario usuario = (Usuario) userDetails;

            return new LoginResponse(
                    this.jwtService.generateAccessToken(userDetails),
                    this.jwtService.generateRefreshToken(userDetails),
                    this.jwtService.getJwtAccessTokenExpiration(),
                    this.jwtService.getJwtRefreshTokenExpiration(),
                    this.usuarioMapper.converterParaUsuarioResponse(usuario)
            );
        } catch (Exception e) {
            throw new RegraNegocioException("Erro ao validar o refresh token");
        }
    }

}