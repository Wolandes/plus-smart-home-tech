package ru.yandex.practicum.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.UUID;

/**
 * Трансфер - корзины товаров в онлайн магазине.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartDto {
    /**
     * Уникальный идентификатор корзины
     */
    UUID id;

    /**
     * Коллекция продуктов
     */
    @NotEmpty(message = "Продукты не могут быть пустыми")
    Map<UUID, Long> products;
}
