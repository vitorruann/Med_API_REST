package med.voll.api.system.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import med.voll.api.domain.user.UserJPA;

@Service
public class TokenService {
    
    @Value("${api.security.token.secret}")
    private String secret;
    @Value("${api.security.token.issuer}")
    private String issuer;
    @Value("${api.data.time-zone}")
    private String timeZone;
    @Value("${api.data.expiration.extra-hours}")
    private int extraHours;

    public String newToken(UserJPA user) {

        try {
            var algorithm = Algorithm.HMAC256(secret);
            
            return JWT.create()
                .withIssuer(issuer)
                .withSubject(user.getLogin())
                .withExpiresAt(expirationDate())
                .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token JWT", exception);
        }
        
    }

    public String geStringSubject(String tokenJWT) {

        try {
            var algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                .withIssuer(issuer)
                .build()
                .verify(tokenJWT)
                .getSubject();
                
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }

    }

    private Instant expirationDate() {
        return LocalDateTime.now().plusHours(extraHours).toInstant(ZoneOffset.of(timeZone));
    }

}
