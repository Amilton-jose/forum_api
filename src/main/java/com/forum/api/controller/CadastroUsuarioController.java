package com.forum.api.controller;

import com.forum.api.model.usuario.Usuario;
import com.forum.api.requests.usuario.DadosAtualizarUsuario;
import com.forum.api.requests.usuario.DadosCadastroUsuario;
import com.forum.api.requests.usuario.DadosListagemUsuario;
import com.forum.api.requests.usuario.UsuarioDTO;
import com.forum.api.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping("usuarios")
@RequiredArgsConstructor
public class CadastroUsuarioController {
    private final UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> cadastro(@RequestBody @Valid DadosCadastroUsuario dados){
        if (usuarioService.ifExistsUser(dados.nome())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Nome já existe");
        }
        Usuario usuario = usuarioService.cadastrar(dados);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioDTO(usuario));
    }

    @GetMapping
    public ResponseEntity<Object> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable){
        Page<DadosListagemUsuario> map = usuarioService.listar(pageable).map(DadosListagemUsuario::new);
        return ResponseEntity.ok(map);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> atualizar(@PathVariable("id") Long id,@RequestBody DadosAtualizarUsuario dados){
        Optional<Usuario> ifAtualizar = usuarioService.atualizar(id);
        if (ifAtualizar.isPresent()){
            Usuario usuario = usuarioService.atualizar(id).get();
            usuario.atualizarInfo(dados);
            return ResponseEntity.status(HttpStatus.OK).body("Registro atualizado!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrado!");
    }

}
