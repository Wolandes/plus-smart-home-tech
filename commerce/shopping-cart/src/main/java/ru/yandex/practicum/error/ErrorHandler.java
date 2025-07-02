package ru.yandex.practicum.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yandex.practicum.error.model.ErrorResponse;

/**
 * Контроллер перехвата ошибок
 */
@RestControllerAdvice
public class ErrorHandler {

    /**
     * Обработка не авторизованного пользователя
     *
     * @param e аргумент ошибки
     * @return возврат ошибки
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleNotAuthorizedUserException(final NotAuthorizedUserException e) {
        return new ErrorResponse(
                e.getCause(),
                e.getStackTrace(),
                HttpStatus.UNAUTHORIZED,
                e.getMessage(),
                e.getMessage(),
                e.getSuppressed(),
                e.getLocalizedMessage()
        );
    }

    /**
     * Обработка - "Нет искомых товаров в корзине"
     *
     * @param e аргумент ошибки
     * @return возврат ошибки
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNoProductsInShoppingCartException(final NoProductsInShoppingCartException e){
        return new ErrorResponse(
                e.getCause(),
                e.getStackTrace(),
                HttpStatus.BAD_REQUEST,
                e.getMessage(),
                e.getMessage(),
                e.getSuppressed(),
                e.getLocalizedMessage()
        );
    }
}
