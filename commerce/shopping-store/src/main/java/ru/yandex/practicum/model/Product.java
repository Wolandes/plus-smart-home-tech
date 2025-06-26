package ru.yandex.practicum.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.yandex.practicum.model.ProductCategory;
import ru.yandex.practicum.model.ProductState;
import ru.yandex.practicum.model.QuantityState;


/**
 * Продукт
 */
@Entity
@Table(name = "products")
@Getter
@Setter
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    /**
     * Уникальный идентификатор продукта.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    String productName;

    /**
     * Описание продукта
     */
    String description;

    /**
     * Ссылка на картинку во внешнем хранилище или SVG.
     */
    String imageSrc;

    /**
     * Статус, перечисляющий состояние остатка как свойства товара
     */
    @Enumerated(value = EnumType.STRING)
    ProductState productState;

    /**
     * Состояние товара
     */
    @Enumerated(value = EnumType.STRING)
    ProductCategory productCategory;

    /**
     * Цена товара
     */
    Double price;
}
