package foroAlura_challenge.APIbyVhall.domain.topico.validaciones;

import foroAlura_challenge.APIbyVhall.domain.topico.DatosRegistroTopico;
import foroAlura_challenge.APIbyVhall.domain.topico.TopicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExisteTopico implements ValidaTopico{

    @Autowired
    TopicoRepository topicoRepository;

    @Override
    public void validarTopico(DatosRegistroTopico datos) {
        var existeTitulo = topicoRepository.findAllByTitulo(datos.titulo());
        var existeMensaje = topicoRepository.findAllByMensaje(datos.mensaje());
        if (existeTitulo != null){
            throw new ValidationException("Ya existe un topico con ese Titulo");
        }
        if (existeMensaje != null){
            throw new ValidationException("Ya existe un topico con ese Mensaje");
        }
    }
}
