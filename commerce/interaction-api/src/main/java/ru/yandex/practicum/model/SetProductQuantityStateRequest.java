package ru.yandex.practicum.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Запрос на изменение статуса остатка товара.
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class SetProductQuantityStateRequest {
    /**
     * Идентификатор товара.
     */
    UUID productId;

    /**
     * Статус, перечисляющий состояние остатка как свойства товара.
     */
    QuantityState quantityState;
}
