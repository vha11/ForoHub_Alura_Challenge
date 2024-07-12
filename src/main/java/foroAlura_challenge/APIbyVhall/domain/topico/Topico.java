package foroAlura_challenge.APIbyVhall.domain.topico;

import foroAlura_challenge.APIbyVhall.domain.curso.Curso;
import foroAlura_challenge.APIbyVhall.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @Column(unique = true)
    private String mensaje;
    private LocalDateTime fecha_creacion;
    @Column(columnDefinition = "TINYINT")
    private Boolean estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    public Topico(String titulo, String mensaje, Usuario autor, Curso curso) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fecha_creacion = LocalDateTime.now();
        this.estado = false;
        this.autor = autor;
        this.curso = curso;
    }

    public void actualizarTopico(DatosActualizarTopico datosActualizarTopico) {

        if (!datosActualizarTopico.titulo().isEmpty()) {
            this.titulo = datosActualizarTopico.titulo();
        }
        if (!datosActualizarTopico.mensaje().isEmpty()) {
            this.mensaje = datosActualizarTopico.mensaje();
        }
    }

}
