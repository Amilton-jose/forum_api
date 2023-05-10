package com.forum.api.controller;

import com.forum.api.model.resposta.Resposta;
import com.forum.api.requests.resposta.DadosAtualizacaoResposta;
import com.forum.api.requests.resposta.DadosCadastroResposta;
import com.forum.api.requests.resposta.DadosDetalhamentoRespota;
import com.forum.api.requests.resposta.DadosListagemResposta;
import com.forum.api.service.RespostaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("respostas")
@RequiredArgsConstructor
public class RespostaController {

    private final RespostaService respostaService;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid DadosCadastroResposta dados){
        Resposta resposta = respostaService.cadastrar(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(new DadosDetalhamentoRespota(resposta));
    }

    @GetMapping()
    public ResponseEntity<Object> listar(@PageableDefault(size = 10) Pageable pageable) {
        var page = respostaService.listar(pageable).map(DadosListagemResposta::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> atualizar(@PathVariable("id") Long id,@RequestBody @Valid DadosAtualizacaoResposta dados) {
        Optional<Resposta> resposta = respostaService.atualizar(id);
        if (resposta.isPresent()){
            resposta.get().atualizarInfo(dados);
            return ResponseEntity.status(HttpStatus.OK).body(new DadosDetalhamentoRespota(resposta.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrado!");
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> deletar(@PathVariable("id") Long id) {
        if(respostaService.deletar(id)){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrado!");
    }
}
