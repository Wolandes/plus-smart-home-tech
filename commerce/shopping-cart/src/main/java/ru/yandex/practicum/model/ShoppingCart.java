package ru.yandex.practicum.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Map;
import java.util.UUID;

/**
 * Сущность - корзины товаров в онлайн магазине.
 */
@Entity
@Table(name = "cart")
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {
    /**
     * Уникальный идентификатор корзины
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    /**
     * Имя пользователя
     */
    @Column(name = "user_name", nullable = false, length = 255)
    private String userName;

    /**
     * Таблица корзины - продуктов
     */
    @ElementCollection
    @CollectionTable(
            name = "cart_products",
            joinColumns = @JoinColumn(name = "cart_id"),
            foreignKey = @ForeignKey(name = "fk_cart_items_cart")
    )
    @MapKeyColumn(name = "product_id")
    @Column(name = "quantity", nullable = false)
    private Map<UUID, Long> products;
}
