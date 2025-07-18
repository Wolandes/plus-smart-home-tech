package ru.yandex.practicum.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Сущность платежа
 */
@Entity
@Table(name = "payment")
@Getter
@Setter
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    /**
     * Уникальный идентификатор платежа
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "payment_id")
    UUID paymentId;

    /**
     * Уникальный идентификатор заказа
     */
    @Column(name = "order_id")
    UUID orderId;

    /**
     * Состояние платежа
     */
    @Enumerated(value = EnumType.STRING)
    PaymentState state;

    /**
     * Общая стоимость.
     */
    @Column(name = "total_cost")
    BigDecimal totalPayment;

    /**
     * Стоимость доставки.
     */
    @Column(name = "delivery_cost")
    BigDecimal deliveryTotal;

    /**
     * Стоимость налога.
     */
    @Column(name = "fee_cost")
    BigDecimal feeTotal;
}

