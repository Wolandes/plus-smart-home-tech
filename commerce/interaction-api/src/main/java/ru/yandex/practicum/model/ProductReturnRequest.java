package ru.yandex.practicum.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Map;
import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
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
