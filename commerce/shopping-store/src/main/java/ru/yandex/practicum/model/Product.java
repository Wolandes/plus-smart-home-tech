package ru.yandex.practicum.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.yandex.practicum.model.ProductCategory;
import ru.yandex.practicum.model.ProductState;
import ru.yandex.practicum.model.QuantityState;

import java.util.UUID;


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
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "product_id")
    UUID productId;

    /**
     * Имя продукта
     */
    @Column(name = "product_name")
    String productName;

    /**
     * Описание продукта
     */
    String description;

    /**
     * Ссылка на картинку во внешнем хранилище или SVG.
     */
    @Column(name = "image_src")
    String imageSrc;

    /**
     * Доступность товара
     */
    @Column(name = "quantity_state")
    QuantityState quantityState;

    /**
     * Статус, перечисляющий состояние остатка как свойства товара
     */
    @Enumerated(value = EnumType.STRING)
    @Column(name = "product_state")
    ProductState productState;

    /**
     * Состояние товара
     */
    @Enumerated(value = EnumType.STRING)
    @Column(name = "product_category")
    ProductCategory productCategory;

    /**
     * Цена товара
     */
    Double price;
}
