package ru.yandex.practicum.eror;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yandex.practicum.eror.model.ErrorResponse;

/**
 * Контроллер перехвата ошибок
 */
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(final NoDeliveryFoundException e) {
        return new ErrorResponse(
                e.getCause(),
                e.getStackTrace(),
                HttpStatus.NOT_FOUND,
                e.getMessage(),
                e.getMessage(),
                e.getSuppressed(),
                e.getLocalizedMessage()
        );
    }
}
