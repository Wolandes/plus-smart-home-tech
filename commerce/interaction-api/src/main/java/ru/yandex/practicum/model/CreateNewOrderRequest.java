package ru.yandex.practicum.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Создание заказа
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class CreateNewOrderRequest {
    /**
     * Имя пользователя
     */
    @NotBlank(message = "Имя пользователя не может быть пустым")
    String userName;

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
