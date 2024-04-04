package uz.abdurahmon.cachespringproject.exceptions;


public class CustomNotFoundException extends RuntimeException {
    public CustomNotFoundException(String message) {
        super(message);
    }
}