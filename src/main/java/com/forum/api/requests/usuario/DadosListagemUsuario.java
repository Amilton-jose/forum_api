package com.forum.api.requests.usuario;

import com.forum.api.model.usuario.Usuario;

public record DadosListagemUsuario(Long id,String nome, String email) {
    public DadosListagemUsuario(Usuario usuario){
        this(usuario.getId(),usuario.getNome(), usuario.getEmail());
    }
}
