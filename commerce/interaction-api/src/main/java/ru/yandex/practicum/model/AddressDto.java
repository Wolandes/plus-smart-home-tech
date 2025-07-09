package ru.yandex.practicum.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Представление адреса в системе.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
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
