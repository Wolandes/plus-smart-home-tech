package ru.yandex.practicum.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Размеры товара
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DimensionDto {
    /**
     * Ширина товара
     */
    @NotNull(message = "Ширина должна быть указана")
    @Positive(message = "Ширина должна быть указана")
    private Double width;

    /**
     * Высота товара
     */
    @NotNull(message = "Высота должна быть указана")
    @Positive(message = "Высота должна быть указана")
    private Double height;

    /**
     * Глубина товара
     */
    @NotNull(message = "Глубина должна быть указана")
    @Positive(message = "Глубина должна быть указана")
    private Double depth;
}
