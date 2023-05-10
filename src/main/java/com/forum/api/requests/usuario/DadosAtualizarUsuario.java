package com.forum.api.requests.usuario;

import com.forum.api.model.usuario.Usuario;

public record DadosAtualizarUsuario(String email) {
    public DadosAtualizarUsuario(Usuario usuario){
        this(usuario.getEmail());
    }
}
