package ru.yandex.practicum.model;

import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

/**
 *Трансферный объект, содержащий данные о продукте.
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    /**
     * Уникальный идентификатор продукта
     */

    Long id;

    /**
     * Имя продукта
     */
    @NotBlank(message = "Имя продукта не может быть пустым.")
    String productName;

    /**
     * Описание продукта
     */
    @NotBlank(message = "Описание товара не может быть пустым.")
    String description;

    /**
     * Ссылка на картинку во внешнем хранилище или SVG.
     */
    String imageSrc;

    /**
     * Статус, перечисляющий состояние остатка как свойства товара
     */
    @NotNull(message = "Состояние количества должен быть указан.")
    ProductState productState;

    /**
     * Состояние товара
     */
    @NotNull(message = "Состояние товара должен быть указан.")
    ProductCategory productCategory;

    /**
     * Цена товара
     */
    @NotNull(message = "Цена товара должен быть указан.")
    @Length(min = 1, message = "Минимальная цена товара - 1")
    Double price;
}
