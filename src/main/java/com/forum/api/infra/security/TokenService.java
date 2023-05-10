package com.forum.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.forum.api.model.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${JWT_SECRET:12345678}")
    private String secret;
    public String gerarToken(Usuario usuario){
        try {
            Algorithm algoritimo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("forum_api")
                    .withSubject(usuario.getNome())
                    .withClaim("id",usuario.getId())
                    .withClaim("email",usuario.getEmail())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritimo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("erro ao gerar token jwt", exception);
        }
    }

    public String getSubject(String tokenJWT){
        try {
            Algorithm algoritimo = Algorithm.HMAC256(secret);
            return JWT.require(algoritimo)
                    // specify an specific claim validations
                    .withIssuer("forum_api")
                    // reusable verifier instance
                    .build()
                    .verify(tokenJWT)
                    .getSubject();

        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
