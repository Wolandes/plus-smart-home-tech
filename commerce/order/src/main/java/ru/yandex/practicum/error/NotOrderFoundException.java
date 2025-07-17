package ru.yandex.practicum.error;

public class NotOrderFoundException extends RuntimeException {
    public NotOrderFoundException(String message) {
        super(message);
    }
}
