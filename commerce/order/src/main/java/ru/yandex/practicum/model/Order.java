package ru.yandex.practicum.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@Setter
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    /**
     * Уникальный идентификатор заказа
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "order_id")
    UUID orderId;

    /**
     * Имя пользователя
     */
    @Column(name = "user_name")
    String userName;

    /**
     * Уникальный идентификатор корзины
     */
    @Column(name = "cart_id")
    UUID shoppingCartId;

    /**
     * Коллекция продуктов
     */
    @ElementCollection
    @CollectionTable(name="order_products", joinColumns = @JoinColumn(name = "order_id"))
    @MapKeyColumn(name = "product_id")
    @Column(name = "quantity")
    Map<UUID, Integer> products;

    /**
     * Идентификатор оплаты.
     */
    @Column(name = "payment_id")
    UUID paymentId;

    /**
     * Идентификатор доставки.
     */
    @Column(name = "delivery_id")
    UUID deliveryId;

    /**
     * Статус заказа.
     */
    @Enumerated(value = EnumType.STRING)
    @Column(name = "state")
    OrderStatus state;

    /**
     * Общий вес доставки.
     */
    @Column(name = "delivery_weight")
    Double deliveryWeight;

    /**
     * Общий объём доставки.
     */
    @Column(name = "delivery_volume")
    Double deliveryVolume;

    /**
     * Признак хрупкости заказа.
     */
    boolean fragile;

    /**
     * Общая стоимость.
     */
    @Column(name = "total_price")
    Double totalPrice;

    /**
     * Стоимость доставки.
     */
    @Column(name = "delivery_price")
    Double deliveryPrice;

    /**
     * Стоимость товаров в заказе.
     */
    @Column(name = "product_price")
    Double productPrice;
}
