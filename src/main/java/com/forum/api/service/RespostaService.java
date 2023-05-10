package com.forum.api.service;

import com.forum.api.model.resposta.Resposta;
import com.forum.api.model.topico.Topico;
import com.forum.api.model.usuario.Usuario;
import com.forum.api.repository.resposta.RespostaRepository;
import com.forum.api.requests.resposta.DadosCadastroResposta;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RespostaService {

    private final RespostaRepository repository;
    private final TopicoService topicoService;
    private final UsuarioService usuarioService;

    public Resposta cadastrar(DadosCadastroResposta dados){
       Topico topico = topicoService.findById(dados.topicoId())
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico não encontrado"));
        Usuario autor = usuarioService.findById(dados.autorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuário não encontrado!"));

        Resposta resposta = new Resposta(dados.mensagem(),topico,autor);
        resposta.setSolucao(dados.solucao());
        return repository.save(resposta);
    }

    public Page<Resposta> listar(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<Resposta> atualizar(Long id) {
        return repository.findById(id);
    }
    public boolean deletar(Long id) {
        Optional<Resposta> exists = repository.findById(id);
        if (exists.isPresent()){
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
