package foroAlura_challenge.APIbyVhall.controllers;

import foroAlura_challenge.APIbyVhall.domain.usuario.*;
import foroAlura_challenge.APIbyVhall.servidor.security.SecurityFilter;
import foroAlura_challenge.APIbyVhall.domain.usuario.DatosModificaPassword;
import foroAlura_challenge.APIbyVhall.domain.usuario.DatosRegistroUsuario;
import foroAlura_challenge.APIbyVhall.domain.usuario.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    private UserService userService;

    @Autowired
    SecurityFilter securityFilter;

    @PostMapping
    @Transactional
    @Operation(summary = "Registra un nuevo usuario en la base de datos")
    public ResponseEntity<?> registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario) {

            userService.registrarUsuario(datosRegistroUsuario);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Usuario creado Correctamente");
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Modifica la contraseña del usuario en la base de datos")
    public  ResponseEntity<?> modificaPassword(@RequestBody @Valid DatosModificaPassword datosModificaPassword) {

        userService.modificarPassword(datosModificaPassword);
        return ResponseEntity.ok("Contraseña modificada exitosamente");

    }

}
