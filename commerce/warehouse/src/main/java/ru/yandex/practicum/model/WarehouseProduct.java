package ru.yandex.practicum.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

/**
 * Сущность склада
 */
@Entity
@Table(name = "warehouse_product")
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseProduct {
    /**
     * Уникальный индефикатор продукта
     */
    @Id
    @Column(name = "product_id")
    UUID productId;

    /**
     * Хрупкость товара
     */
    boolean fragile;

    /**
     * Ширина товара
     */
    Double width;

    /**
     * Высота товара
     */
    Double height;

    /**
     * Глубина товара
     */
    Double depth;

    /**
     * Вес товара
     */
    Double weight;

    /**
     * Количество товара
     */
    long quantity = 0L;
}
