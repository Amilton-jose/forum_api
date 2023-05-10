package com.forum.api.requests.usuario;

import com.forum.api.model.usuario.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email) {
        public UsuarioDTO(Usuario usuario) {
                this(usuario.getNome(),usuario.getEmail());
        }
}
