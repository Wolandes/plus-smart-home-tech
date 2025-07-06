package ru.yandex.practicum.model;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Трансферная сущность бронирования товара
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookedProductsDto {
    /**
     * Вес доставки
     */
    @PositiveOrZero(message = "Вес доставки должен быть положительным или нулевым")
    Double deliveryWeight;

    /**
     * Объем доставки
     */
    @PositiveOrZero(message = "Объем доставки должен быть положительным или нулевым")
    Double deliveryVolume;

    /**
     * Хрупкость товара
     */
    Boolean fragile;
}
