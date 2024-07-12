package foroAlura_challenge.APIbyVhall.domain.topico.validaciones;

import foroAlura_challenge.APIbyVhall.domain.topico.DatosRegistroTopico;
import foroAlura_challenge.APIbyVhall.domain.usuario.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExisteUsuario implements ValidaTopico {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void validarTopico (DatosRegistroTopico datos) {
        var existeUsuario = usuarioRepository.existsById(datos.autor());

        if (!existeUsuario) {
            throw new ValidationException("Autor inexistente o no registrado en el sistema");
        }
    }
}
