package foroAlura_challenge.APIbyVhall.servidor.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import foroAlura_challenge.APIbyVhall.domain.usuario.Usuario;
import foroAlura_challenge.APIbyVhall.servidor.excepciones.TokenHasExpiredException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;

    public String generarToken(Usuario usuario) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            var token = JWT.create()
                    .withIssuer("Foro Alura")
                    .withSubject(usuario.getUsername())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaDeExpiracion())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }

    public String getSubject(String token) {
        if (token == null){
            throw new RuntimeException();
        }
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret); // Validando firma
            verifier = JWT.require(algorithm)
                    .withIssuer("Foro Alura")
                    .build()
                    .verify(token);
            verifier.getSubject();

        } catch (JWTVerificationException exception) {
            throw new TokenHasExpiredException("El token ha expirado");
        }
        if (verifier == null) {
            throw new TokenHasExpiredException("Verificación Invalida");
        }
        return  verifier.getSubject();
    }

    private Instant generarFechaDeExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
