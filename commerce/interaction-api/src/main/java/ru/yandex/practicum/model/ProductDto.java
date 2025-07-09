package ru.yandex.practicum.model;

import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

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
    UUID productId;

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
     * Доступность товара
     */
    @NotNull(message = "Состояние товара должен быть указан.")
    QuantityState quantityState;

    /**
     * Статус, перечисляющий состояние остатка как свойства товара
     */
    @NotNull(message = "Состояние количества должен быть указан.")
    ProductState productState;

    /**
     * Состояние товара
     */
    @NotNull(message = "Категория устройств должен быть указан.")
    ProductCategory productCategory;

    /**
     * Цена товара
     */
    @NotNull(message = "Цена товара должен быть указан.")
    @DecimalMin(value = "0.01", message = "Минимальная цена товара 0.01")
    Double price;
}
