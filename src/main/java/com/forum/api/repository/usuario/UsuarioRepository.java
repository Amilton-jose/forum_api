package com.forum.api.repository.usuario;

import com.forum.api.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


    UserDetails findByNome(String nome);
    boolean existsByNome(String nome);
}
