package com.forum.api.service;

import com.forum.api.model.usuario.Usuario;
import com.forum.api.repository.usuario.UsuarioRepository;
import com.forum.api.requests.usuario.DadosCadastroUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;
    public Optional<Usuario> findById(Long id){
       return usuarioRepository.findById(id);
    }

    public Usuario cadastrar(DadosCadastroUsuario dados){
        Usuario usuario = new Usuario(dados);
        usuario.setSenha(passwordEncoder.encode(dados.senha()));
        return usuarioRepository.save(usuario);
    }


    public Page<Usuario> listar(Pageable pageable){
        return usuarioRepository.findAll(pageable);
    }

    public Optional<Usuario> atualizar(Long id){
        return usuarioRepository.findById(id);
    }

    public boolean ifExistsUser(String nome){
        boolean existsNomeUser = usuarioRepository.existsByNome(nome);
        if (existsNomeUser){
            return true;
        }
        return false;
    }

    public boolean deletar(Long id){
        if (usuarioRepository.findById(id).isEmpty()){
            return false;
        }
        usuarioRepository.deleteById(id);
        return true;
    }
}
