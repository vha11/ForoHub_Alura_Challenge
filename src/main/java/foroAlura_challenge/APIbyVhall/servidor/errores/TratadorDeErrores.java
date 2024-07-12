package foroAlura_challenge.APIbyVhall.servidor.errores;


import foroAlura_challenge.APIbyVhall.servidor.excepciones.EmailAlreadyExistsException;
import foroAlura_challenge.APIbyVhall.servidor.excepciones.InvalidPasswordException;
import foroAlura_challenge.APIbyVhall.servidor.excepciones.TokenHasExpiredException;
import foroAlura_challenge.APIbyVhall.servidor.excepciones.UsuarioNoEncontradoException;
import jakarta.validation.ValidationException;
import org.hibernate.action.internal.EntityActionVetoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErrores {

    @ExceptionHandler(EntityActionVetoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity tratarError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e) {
        var errores = e.getFieldErrors().stream()
                .map(DatosErrorValidacion::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(ValidacionDeIntegridad.class)
    public ResponseEntity errorHandlerValidacionDeIntegridad(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity errorHandlerErrorDeNegocio(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity errorHandlerEmailYaExiste(EmailAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(UsuarioNoEncontradoException.class)
    public ResponseEntity<?> manejarUsuarioNoEncontrado(UsuarioNoEncontradoException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<?> manejarInvalidPassword(InvalidPasswordException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    @ExceptionHandler(TokenHasExpiredException.class)
    public ResponseEntity<?> handleTokenHasExpiredException(TokenHasExpiredException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    private record DatosErrorValidacion(String campo, String Error) {
        public DatosErrorValidacion(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
