package com.forum.api.model.usuario;

import com.forum.api.model.resposta.Resposta;
import com.forum.api.requests.topico.DadosAtualizacaoTopico;
import com.forum.api.requests.usuario.DadosAtualizarUsuario;
import com.forum.api.requests.usuario.DadosCadastroUsuario;
import com.forum.api.requests.usuario.UsuarioDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false,length = 100,unique = true)
    private String nome;

    @Column(nullable = false, length = 100)
    private String email;

    private String senha;


    public Usuario(UsuarioDTO autor) {
        this.nome = autor.nome();
        this.email = autor.email();
    }

    public Usuario(DadosCadastroUsuario dados) {
        this.nome = dados.nome();
        this.email = dados.email();
    }

    public void atualizarAutor(DadosAtualizacaoTopico dados) {
        if (dados.autor().nome() != null) {
            this.nome = dados.autor().nome();
        }
        if(dados.autor().email() != null) {
            this.email = dados.autor().email();
        }
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.nome;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void atualizarInfo(DadosAtualizarUsuario dados) {
        this.email = dados.email();
    }
}
