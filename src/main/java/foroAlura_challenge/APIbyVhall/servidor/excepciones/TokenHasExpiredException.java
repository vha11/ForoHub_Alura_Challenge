package foroAlura_challenge.APIbyVhall.servidor.excepciones;

public class TokenHasExpiredException extends RuntimeException{
    public TokenHasExpiredException(String mensaje) {
        super(mensaje);
    }
}
