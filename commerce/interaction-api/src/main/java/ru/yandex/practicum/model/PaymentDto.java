package ru.yandex.practicum.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class PaymentDto {
    /**
     * Уникальный идентификатор заказа
     */
    UUID paymentId;

    /**
     * Общая стоимость.
     */
    Double totalPayment;

    /**
     * Стоимость доставки.
     */
    Double deliveryTotal;

    /**
     * Стоимость налога.
     */
    Double feeTotal;
}
