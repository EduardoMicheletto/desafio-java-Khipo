package com.api.testeKhipo.service;

import com.api.testeKhipo.config.JwtService;
import com.api.testeKhipo.model.response.AuthenticationResponse;
import com.api.testeKhipo.model.requests.LoginRequest;
import com.api.testeKhipo.model.requests.ResgistroRequest;
import com.api.testeKhipo.model.entity.Usuario;
import com.api.testeKhipo.model.entity.enums.Acessos;
import com.api.testeKhipo.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse registro(ResgistroRequest registro) {
        if(usuarioRepository.findByLogin(registro.login()).isPresent()){
            return AuthenticationResponse.builder().error("Nome de usuário já em uso.").build();
        }

        var usuario = Usuario.builder()
                .nome(registro.nome())
                .login(registro.login())
                .email(registro.email())
                .password(passwordEncoder.encode(registro.senha()))
                .acessos(Acessos.USER)
                .build();

        usuarioRepository.save(usuario);

        return extrairAuthenticationResponse(usuario);
    }

    public AuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.login(),
                        request.password()
                )
        );

        var usuario = usuarioRepository.findByLogin(request.login())
                .orElseThrow();

        return extrairAuthenticationResponse(usuario);
    }

    private AuthenticationResponse extrairAuthenticationResponse(Usuario usuario) {
        var token = jwtService.generateToken(usuario);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

}
