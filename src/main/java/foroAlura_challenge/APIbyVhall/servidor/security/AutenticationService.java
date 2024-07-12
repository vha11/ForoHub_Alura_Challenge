package foroAlura_challenge.APIbyVhall.servidor.security;

import foroAlura_challenge.APIbyVhall.domain.usuario.UsuarioRepository;
import foroAlura_challenge.APIbyVhall.servidor.excepciones.UsuarioNoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticationService implements UserDetailsService{

    @Autowired
    private UsuarioRepository usuarioRepository;

   @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       var userEmail = usuarioRepository.findByEmail(username);
       if (userEmail != null) {
           return userEmail;
       }
       throw new UsuarioNoEncontradoException("Usuario inexistente");
    }
}
