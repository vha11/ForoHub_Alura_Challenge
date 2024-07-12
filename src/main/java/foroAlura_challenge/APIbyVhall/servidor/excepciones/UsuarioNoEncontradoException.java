package foroAlura_challenge.APIbyVhall.servidor.excepciones;

public class UsuarioNoEncontradoException extends RuntimeException {

    public UsuarioNoEncontradoException (String mensaje) {
        super(mensaje);
    }
}
