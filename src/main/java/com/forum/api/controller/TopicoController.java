package com.forum.api.controller;

import com.forum.api.model.topico.Topico;
import com.forum.api.requests.topico.DadosAtualizacaoTopico;
import com.forum.api.requests.topico.DadosCadastroTopico;
import com.forum.api.requests.topico.DadosListagemTopico;
import com.forum.api.requests.topico.DadosDetalhamentoTopico;
import com.forum.api.service.TopicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("topicos")
@RequiredArgsConstructor
public class TopicoController {
    private final TopicoService service;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder uriBuilder) {
        if (service.ifExistsTitulo(dados.titulo())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Este titulo já existe");
        }
        if (service.ifExistsMensagem(dados.mensagem())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Esta mensagem já existe");
        }
        Topico topico = service.cadastrar(dados);
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topico));
    }

    @GetMapping()
    public ResponseEntity<Page<DadosListagemTopico>> listarPorDataCriacao(@PageableDefault(size = 10, sort = {"dataCriacao"}) Pageable pageable) {
        var page = service.listar(pageable).map(DadosListagemTopico::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/find")
    public ResponseEntity<Page<DadosListagemTopico>> listarPorNome(@PageableDefault(size = 10) Pageable pageable, @RequestParam String nome) {
        Page<DadosListagemTopico> page = service.listarPorNome(pageable, nome).map(DadosListagemTopico::new);
        if (!page.isEmpty()) {
            return ResponseEntity.ok(page);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findBy")
    public ResponseEntity<Page<DadosListagemTopico>> listarPorAno(@PageableDefault(size = 10) Pageable pageable, @RequestParam int ano) {
        Page<DadosListagemTopico> page = service.listarPorAno(pageable, ano).map(DadosListagemTopico::new);
        if (!page.isEmpty()) {
            return ResponseEntity.ok(page);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> detalhar(@PathVariable Long id){
        Optional<Topico> topico = service.detalhar(id);
        if (topico.isPresent()){
            return ResponseEntity.ok(new DadosDetalhamentoTopico(topico.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping()
    @Transactional
    public ResponseEntity<Object> atualizar(@RequestBody @Valid DadosAtualizacaoTopico dados) {
       Topico topico = service.atualizar(dados.id());
       topico.atualizarInfo(dados);
       if (topico != null){
           return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
       }
       return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        boolean deletar = service.deletar(id);
        if (deletar == true){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

