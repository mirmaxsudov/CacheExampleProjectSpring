package uz.abdurahmon.cachespringproject.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uz.abdurahmon.cachespringproject.payload.ApiErrorResponse;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        Map<String, String> mp = new LinkedHashMap<>();
        List<ObjectError> errors = exception.getBindingResult().getAllErrors();

        for (ObjectError error : errors) {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            mp.put(field, message);
        }

        return new ResponseEntity<>(mp, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CustomNotFoundException.class)
    private ApiErrorResponse notFound(CustomNotFoundException ex) {
        return ApiErrorResponse
                .builder()
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .when(LocalDateTime.now())
                .build();
    }
}