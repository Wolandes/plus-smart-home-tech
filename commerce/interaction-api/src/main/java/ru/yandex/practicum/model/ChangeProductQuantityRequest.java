package ru.yandex.practicum.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Запрос на изменение количества единиц товара
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeProductQuantityRequest {
    /**
     * Идентификатор продукта
     */
    @NotNull(message = "Идентификатор продукта не должен быть пустым")
    private UUID productId;

    /**
     * Количество товаров
     */
    @NotNull(message = "Количество товаров не должна быть пустым")
    private Long quantity;
}
