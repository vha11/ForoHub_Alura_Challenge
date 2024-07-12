package foroAlura_challenge.APIbyVhall.domain.topico;

import java.time.LocalDateTime;
import foroAlura_challenge.APIbyVhall.domain.topico.Topico;

public record DatosDetallesTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha_creacion,
        Boolean estado,
        Long autor,
        Long curso) {

    public DatosDetallesTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFecha_creacion(),
                topico.getEstado(),
                topico.getAutor().getId(),
                topico.getCurso().getId()
        );
    }
}
