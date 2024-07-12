package foroAlura_challenge.APIbyVhall.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String username);
}
