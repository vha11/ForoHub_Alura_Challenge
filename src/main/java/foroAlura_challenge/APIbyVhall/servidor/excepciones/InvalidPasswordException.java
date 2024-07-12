package foroAlura_challenge.APIbyVhall.servidor.excepciones;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String mensaje) {
        super(mensaje);
    }
}
