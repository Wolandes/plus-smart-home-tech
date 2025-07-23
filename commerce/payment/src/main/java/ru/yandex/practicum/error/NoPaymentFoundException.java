package ru.yandex.practicum.error;

public class NoPaymentFoundException extends RuntimeException {
    public NoPaymentFoundException(String message) {
        super(message);
    }
}
