package fr.eni.demospringweb.aop;

import fr.eni.demospringweb.service.BusinessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ControllerAdvice
 * Permet de définir un aspect pour les exceptions qui sont lancées par Spring lorsqu'il ne parvient pas
 * à valider nos contraines de Bean Validation (@NotEmpty, @Min, @Future etc...)
 * */
@ControllerAdvice
public class GlobalExceptionHandler extends  ResponseEntityExceptionHandler {


    /**
     * Cette méthode est appelée lorsqu'un exception de type IllegalArgumentException BusinessException est levée
     */
    @ExceptionHandler({
            IllegalArgumentException.class,
            // + autres exceptions qu'on souhaite renvoyer en tant que BAD_REQUEST
            BusinessException.class
    })
    public ResponseEntity<Object> handleBusinessException(Exception e){
        // On va renvoyée une erreur de type BAD_REQUEST avec le même format de réponse que lorsqu'on a une ereur de validation de Bean
        // => Ca va permettre au Front d'afficher les erreurs de la même manière
        Map<String, List<String>> body = new HashMap<>();
        body.put("errors", List.of(e.getMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    /**
     * Cette méthode est appelée lors de l'échec de la validation Spring (qui lance une MethodArgumentNotValidException)
     */
    @Override
    protected ResponseEntity<Object>
    handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

       // Lorsqe la validation échoue, je crée une Map qui va co,ntenir le Body de ma réponse HTTP
        Map<String, List<String>> body = new HashMap<>();

       // je rempli une liste avec les erreurs de validation que je recupère depuis mon exception
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                // pour créer ma liste j'utilise un map() avec .getDefaultMessage()
                .map(err -> err.getDefaultMessage())
                .collect(Collectors.toList());
        // je rempli le Body de ma réponse avec l'attribut "errors" qui contient ma liste d'erreurs
        body.put("errors", errors);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}