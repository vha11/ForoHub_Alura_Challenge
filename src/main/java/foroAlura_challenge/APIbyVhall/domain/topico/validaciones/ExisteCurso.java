package foroAlura_challenge.APIbyVhall.domain.topico.validaciones;

import foroAlura_challenge.APIbyVhall.domain.curso.CursoRepository;
import foroAlura_challenge.APIbyVhall.domain.topico.DatosRegistroTopico;
import foroAlura_challenge.APIbyVhall.servidor.errores.ValidacionDeIntegridad;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExisteCurso implements ValidaTopico {

    @Autowired
    CursoRepository cursoRepository;

    @Override
    public void validarTopico (DatosRegistroTopico datos){
        if (datos.curso() == null ) {
            throw new ValidacionDeIntegridad("El topico debe tener un curso asignado");
        }
        var existeCurso = cursoRepository.findById(datos.curso());
        if (existeCurso.isEmpty()) {
            throw new ValidationException("Curso Inexistente");
        }
    }
}
