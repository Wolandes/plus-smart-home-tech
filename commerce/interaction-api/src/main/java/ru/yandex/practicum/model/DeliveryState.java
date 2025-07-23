package ru.yandex.practicum.model;

/**
 * Статус доставки.
 */
public enum DeliveryState {
    /**
     * Создание доставки
     */
    CREATED,

    /**
     * Доставка выполняется
     */
    IN_PROGRESS,

    /**
     * Доставлено
     */
    DELIVERED,

    /**
     * Ошибка доставки
     */
    FAILED,

    /**
     * Состояние закрыто
     */
    CANCELLED
}
