package com.forum.api.controller;

import com.forum.api.infra.security.DadosTokenJWT;
import com.forum.api.infra.security.TokenService;
import com.forum.api.model.usuario.DadosUsuarioLogin;
import com.forum.api.model.usuario.Usuario;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager manager;
    private final TokenService tokenService;
    @PostMapping
    @Transactional
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosUsuarioLogin dados){
        var authenticateToken = new UsernamePasswordAuthenticationToken(dados.nome(),dados.senha());
        var authentication = manager.authenticate(authenticateToken);
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

}
