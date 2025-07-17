package ru.yandex.practicum.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
public class OrderDto {
    /**
     * Уникальный идентификатор заказа
     */
    @NotNull
    UUID orderId;

    /**
     * Уникальный идентификатор корзины
     */
    UUID shoppingCartId;

    @NotEmpty
    /**
     *
     */
    Map<UUID, Integer> products;
    UUID paymentId;
    UUID deliveryId;
    OrderStatus state;
    Double deliveryWeight;
    Double deliveryVolume;
    boolean fragile;
    BigDecimal totalPrice;
    BigDecimal deliveryPrice;
    BigDecimal productPrice;
}
