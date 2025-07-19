package ru.yandex.practicum.model;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

/**
 * Запрос о доставке
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryRequest {
    /**
     * Уникальный идентификатор заказа
     */
    @NotNull
    UUID orderId;

    /**
     * Уникальный идентификатор доставки
     */
    @NotNull
    UUID deliveryId;
}
