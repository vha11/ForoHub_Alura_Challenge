package foroAlura_challenge.APIbyVhall.servidor.excepciones;

public class EmailAlreadyExistsException extends RuntimeException{

    public EmailAlreadyExistsException (String mensaje) {
        super(mensaje);
    }
}
