package ru.yandex.practicum.model;

/**
 * Состояние платежа
 */
public enum PaymentState {
    /**
     * Ожидание оплаты
     */
    PENDING,

    /**
     * Завершение платежа
     */
    SUCCESS,

    /**
     * Ошибка платежа
     */
    FAILED
}