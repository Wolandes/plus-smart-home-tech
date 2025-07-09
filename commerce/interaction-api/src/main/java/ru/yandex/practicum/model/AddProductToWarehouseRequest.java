package ru.yandex.practicum.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Запрос на увеличение единиц товара по его идентификатору
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddProductToWarehouseRequest {
    /**
     * Уникальный индефикатор товара
     */
    @NotNull(message = "Индефикатор не может быть пустым")
    private UUID productId;

    /**
     * Количество товаров
     */
    @NotNull(message = "Количество не может быть пустым")
    @Positive(message = "Количество должны быть не меньше 0")
    private Long quantity;
}
