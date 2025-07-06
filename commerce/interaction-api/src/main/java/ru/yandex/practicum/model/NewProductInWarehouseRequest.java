package ru.yandex.practicum.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Запрос на добавление нового товара на склад
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewProductInWarehouseRequest {
    /**
     * Уникальный индефикатор товара
     */
    @NotNull(message = "Product ID is required")
    private UUID productId;

    /**
     * Ломающиеся ли товар
     */
    private boolean fragile;

    /**
     * Размер товара
     */
    @NotNull(message = "Размер не может быть пустым")
    private DimensionDto dimension;

    /**
     * Вес товара
     */
    @NotNull(message = "Вес не может быть пустым")
    @DecimalMin(value = "0.1", message = "Минимальный вес 0.1 кг")
    private Double weight;
}
