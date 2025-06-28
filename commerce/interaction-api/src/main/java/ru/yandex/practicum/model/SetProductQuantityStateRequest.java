package ru.yandex.practicum.model;

/**
 * Запрос на изменение статуса остатка товара.
 */
public class SetProductQuantityStateRequest {
    /**
     * Идентификатор товара.
     */
    Long productId;

    /**
     * Статус, перечисляющий состояние остатка как свойства товара.
     */
    QuantityState quantityState;
}
