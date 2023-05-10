package com.forum.api.service;

import com.forum.api.model.topico.Topico;
import com.forum.api.model.usuario.Usuario;
import com.forum.api.repository.topico.TopicoRepository;
import com.forum.api.requests.topico.DadosCadastroTopico;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TopicoService {

    private final TopicoRepository repository;
    private final UsuarioService usuarioService;

    public Topico cadastrar(DadosCadastroTopico dados) {
        Usuario autor = usuarioService.findById(dados.autorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuário não encontrado!"));
        Topico topico = new Topico(dados,autor);

        return repository.save(topico);
    }

    public Page<Topico> listar(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Topico> listarPorNome(Pageable pageable, String nome){
        return repository.findByCursoNomeContaining(pageable,nome);
    }

    public Page<Topico> listarPorAno(Pageable pageable, int ano){
        LocalDate dataInicio = LocalDate.of(ano,1,1);
        LocalDate dataFim = LocalDate.of(ano,12,31);
        return repository.findAllByDataCriacaoBetween(pageable,dataInicio,dataFim);
    }

    public Optional<Topico> detalhar(Long id){
       return repository.findById(id);
    }

    public Topico atualizar(@RequestParam Long id){
      return  repository.getReferenceById(id);
    }

    public boolean deletar(@PathVariable Long id){
        Optional<Topico> exists = repository.findById(id);
        if (exists.isPresent()){
            repository.deleteById(exists.get().getId());
            return true;
        }
        return false;
    }

    public Optional<Topico> findById(Long id){
        return repository.findById(id);
    }

    public boolean ifExistsTitulo(String titulo){
        boolean existsByTitulo = repository.existsByTitulo(titulo);
        if (existsByTitulo){
            return true;
        }
        return false;
    }

    public boolean ifExistsMensagem(String mensagem){
        boolean existsByMensagem = repository.existsByMensagem(mensagem);
        if (existsByMensagem){
            return true;
        }
        return false;
    }
}
