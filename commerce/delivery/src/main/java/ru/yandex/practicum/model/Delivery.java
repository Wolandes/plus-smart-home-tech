package ru.yandex.practicum.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

/**
 * Сущность доставки
 */
@Entity
@Table(name = "delivery")
@Getter
@Setter
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Delivery {
    /**
     * Уникальный идентификатор доставки
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "delivery_id")
    UUID deliveryId;

    /**
     * Уникальный идентификатор заказа
     */
    @Column(name = "order_id")
    UUID orderId;

    /**
     * Адрес отправителя
     */
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "from_address_id")
    Address senderAddress;

    /**
     * Адрес получателя
     */
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "to_address_id")
    Address recipientAddress;

    /**
     * Статус доставки
     */
    @Enumerated(value = EnumType.STRING)
    @Column(name = "state")
    DeliveryState deliveryStatus;

    /**
     * Вес доставки
     */
    Double deliveryWeight;

    /**
     * Объем доставки
     */
    Double deliveryVolume;

    /**
     * Хрупкость товара
     */
    boolean fragile;
}
