package ru.yandex.practicum.model;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

/**
 * Трансферный объект, содержащий данные о доставке.
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
public class DeliveryDto {
    /**
     * Уникальный идентификатор доставки
     */
    UUID deliveryId;

    /**
     * Адрес отправителя
     */
    @NotNull
    AddressDto senderAddress;

    /**
     * Адрес получателя
     */
    @NotNull
    AddressDto recipientAddress;

    /**
     * Уникальный идентификатор заказа
     */
    @NotNull
    UUID orderId;

    /**
     * Статус доставки
     */
    @NotNull
    DeliveryState deliveryState;
}
