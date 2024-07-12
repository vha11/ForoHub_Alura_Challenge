package foroAlura_challenge.APIbyVhall.domain.usuario;

import foroAlura_challenge.APIbyVhall.servidor.excepciones.EmailAlreadyExistsException;
import foroAlura_challenge.APIbyVhall.servidor.excepciones.UsuarioNoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    
    public Usuario registrarUsuario (DatosRegistroUsuario datosRegistroUsuario) {
        if (usuarioRepository.findByEmail(datosRegistroUsuario.email()) != null) {
            throw new EmailAlreadyExistsException("El correo electrónico ya está en uso");
        }

        Usuario usuario = new Usuario(
                null,
                datosRegistroUsuario.nombre(),
                datosRegistroUsuario.email(),
                datosRegistroUsuario.password());
        String passwordEncoded = passwordEncoder.encode(datosRegistroUsuario.password());
        usuario.setPassword(passwordEncoded);
        return usuarioRepository.save(usuario);
    }

    public void modificarPassword(DatosModificaPassword datosModificaPassword){
       Usuario usuarioExistente = usuarioRepository.findByEmail(datosModificaPassword.email());
       if (usuarioExistente == null) {
           throw new UsuarioNoEncontradoException("Usuario inexistente");
       } else {
           String passwordEncoded = passwordEncoder.encode(datosModificaPassword.password());
           usuarioExistente.setPassword(passwordEncoded);
           usuarioRepository.save(usuarioExistente);
       }
    }
}
