package ru.yandex.practicum.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

/**
 * Сущность адреса доставки
 */
@Entity
@Table(name = "address")
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    /**
     * Уникальный идентификатор заказа
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "address_id")
    UUID addressId;

    /**
     * Страна
     */
    String country;

    /**
     * Город
     */
    String city;

    /**
     * Улица
     */
    String street;

    /**
     * Дом
     */
    String house;

    /**
     * Квартира
     */
    String flat;
}
