package foroAlura_challenge.APIbyVhall.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record DatosModificaPassword (
        String email,
        @NotBlank
        @Size(min = 8, max = 12, message = "La contraseña debe tener entre 8 y 12 caracteres")
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$", message = "La contraseña debe contener" +
                " al menos un número, una letra mayúscula y un caracter especial")
        String password ){
}
