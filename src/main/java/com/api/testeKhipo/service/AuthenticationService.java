package com.api.testeKhipo.service;

import com.api.testeKhipo.config.JwtService;
import com.api.testeKhipo.dto.AuthenticationResponse;
import com.api.testeKhipo.dto.LoginDTO;
import com.api.testeKhipo.dto.ResgistroDTO;
import com.api.testeKhipo.entity.Usuario;
import com.api.testeKhipo.entity.enums.Acessos;
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

    public AuthenticationResponse registro(ResgistroDTO registro) {
        if(usuarioRepository.findByLogin(registro.getLogin()).isPresent()){
            return AuthenticationResponse.builder().error("Nome de usuário já em uso.").build();
        }

        var usuario = Usuario.builder()
                .nome(registro.getNome())
                .login(registro.getLogin())
                .email(registro.getEmail())
                .password(passwordEncoder.encode(registro.getSenha()))
                .acessos(Acessos.USER)
                .build();

        usuarioRepository.save(usuario);

        return extrairAuthenticationResponse(usuario);
    }

    public AuthenticationResponse login(LoginDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getSenha()
                )
        );

        var usuario = usuarioRepository.findByLogin(request.getLogin())
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
