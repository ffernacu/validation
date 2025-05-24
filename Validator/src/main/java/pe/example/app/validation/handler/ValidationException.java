package pe.example.app.validation.handler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import java.util.Set;

public class ValidationException extends ConstraintViolationException {
    public ValidationException(Set<? extends ConstraintViolation<?>> constraintViolations) {
        super(constraintViolations);
    }
}
