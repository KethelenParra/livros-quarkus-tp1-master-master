package br.unitins.topicos1.service.jwt.serviceImpl;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import br.unitins.topicos1.dto.Response.UsuarioResponseDTO;
import br.unitins.topicos1.service.jwt.JwtService;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JwtServiceImpl implements JwtService {

    private static final Duration EXPIRATION_TIME = Duration.ofHours(24);

    @Override
    public String generateJwt(UsuarioResponseDTO dto, int perfil) {
        Instant now = Instant.now();
        Instant expiryDate = now.plus(EXPIRATION_TIME);

        Set<String> roles = new HashSet<String>();
    
        if(perfil == 1){
            roles.add("Funcionario");
        }else if (perfil == 2){
            roles.add("Cliente");
        }
      
        return Jwt.issuer("unitins-jwt")
            .subject(dto.nome())
            .groups(roles)
            .expiresAt(expiryDate)
            .sign();
    }
    
}
