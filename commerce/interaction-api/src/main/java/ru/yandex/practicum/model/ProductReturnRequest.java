package ru.yandex.practicum.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Map;
import java.util.UUID;

public class ProductReturnRequest {
    /**
     * Уникальный идентификатор заказа
     */
    @NotNull(message = "Идентификатор товара должен быть указан")
    UUID orderId;

    /**
     * Коллекция продуктов
     */
    @NotEmpty(message = "Коллекция продуктов не должен быть пусты")
    Map<UUID, Integer> products;
}
