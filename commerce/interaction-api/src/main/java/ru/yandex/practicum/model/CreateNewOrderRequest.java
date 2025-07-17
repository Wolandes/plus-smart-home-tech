package ru.yandex.practicum.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Создание заказа
 */
@Data
@NoArgsConstructor
public class CreateNewOrderRequest {
    /**
     * Корзина
     */
    @NotNull(message = "Корзина должна быть заполнена")
    ShoppingCartDto shoppingCart;

    /**
     * Представление адреса в системе.
     */
    @NotNull(message = "Адресс должен быть заполнен")
    AddressDto deliveryAddress;
}
