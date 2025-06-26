package ru.yandex.practicum.model;

/**
 * Доступность товара
*/

public enum QuantityState {
    /**
     * Товар закончился
    */
    ENDED,

    /**
     * Осталось меньше 10 единиц товара
     */
    FEW,

    /**
     * Осталось от 10 до 100 единиц
     */
    ENOUGH,

    /**
     * Осталось больше 100 единиц
     */
    MANY
}
