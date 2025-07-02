package ru.yandex.practicum.error;

/**
 * Исключения не авторизованный пользователь
 */
public class NotAuthorizedUserException extends RuntimeException {
    public NotAuthorizedUserException(String message) {
        super(message);
    }
}
