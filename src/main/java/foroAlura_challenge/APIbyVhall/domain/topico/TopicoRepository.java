package foroAlura_challenge.APIbyVhall.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {

    Topico findAllByTitulo(String titulo);

    Topico findAllByMensaje (String mensaje);

    Page <Topico> findAllByCurso(String curso, Pageable paginacion);

    Page <Topico> findAllByEstadoTrue(Pageable paginacion);

    Page<Topico> findAllByEstadoFalse(Pageable paginacion);


    @Query("""
            SELECT t FROM Topico t
            ORDER BY t.fecha_creacion
            """)
    Page<Topico> listarOrdenadosPorFecha(Pageable paginacion);

    @Query("""
            SELECT t FROM Topico t
            WHERE t.autor.id = :userId
            """)
    Page<Topico> findByAutorId(Long userId, Pageable paginacion);

    Page<Topico> findByCursoId(Long cursoId, Pageable paginacion);

    @Query("""
            SELECT t FROM Topico t
            WHERE YEAR(t.fecha_creacion) = :year
            """)
    Page<Topico> findAllByYear(Integer year, Pageable paginacion);
}
