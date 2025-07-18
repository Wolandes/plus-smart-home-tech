package ru.yandex.practicum.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

/**
 * Представление заказа в системе.
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OrderDto {
    /**
     * Уникальный идентификатор заказа
     */
    UUID orderId;

    /**
     * Уникальный идентификатор корзины
     */
    UUID shoppingCartId;

    /**
     * Коллекция продуктов
     */
    @NotEmpty
    Map<UUID, Integer> products;

    /**
     * Идентификатор оплаты.
     */
    UUID paymentId;

    /**
     * Идентификатор доставки.
     */
    UUID deliveryId;

    /**
     * Статус заказа.
     */
    OrderStatus state;

    /**
     * Общий вес доставки.
     */
    Double deliveryWeight;

    /**
     * Общий объём доставки.
     */
    Double deliveryVolume;

    /**
     * Признак хрупкости заказа.
     */
    boolean fragile;

    /**
     * Общая стоимость.
     */
    Double totalPrice;

    /**
     * Стоимость доставки.
     */
    Double deliveryPrice;

    /**
     * Стоимость товаров в заказе.
     */
    Double productPrice;
}
