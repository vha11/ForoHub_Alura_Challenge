package foroAlura_challenge.APIbyVhall.controllers;

import foroAlura_challenge.APIbyVhall.domain.usuario.DatosAutenticacionUsuario;
import foroAlura_challenge.APIbyVhall.domain.usuario.Usuario;
import foroAlura_challenge.APIbyVhall.servidor.security.DatosJWTToken;
import foroAlura_challenge.APIbyVhall.servidor.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @Operation(summary = "Inicio de sesión del usuario ")
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario) {
        if (datosAutenticacionUsuario.email() == null || datosAutenticacionUsuario.email().isEmpty()) {
            return ResponseEntity.badRequest().body("campo del email vacio, registra un email");
        }
        if (datosAutenticacionUsuario.password() == null || datosAutenticacionUsuario.password().isEmpty()) {
            return ResponseEntity.badRequest().body("campo password vacio, registra una contraseña");
        }
        try {
            Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.email(),
                    datosAutenticacionUsuario.password());
            var usuarioAutenticado = authenticationManager.authenticate(authToken);
            var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
            return ResponseEntity.ok(new DatosJWTToken(JWTtoken));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña invalida");
        }
    }
}
