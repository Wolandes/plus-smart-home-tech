package ru.yandex.practicum.mapper;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.model.ShoppingCart;
import ru.yandex.practicum.model.ShoppingCartDto;

/**
 * Mapper для корзины продукта
 */
@Component
public class ShoppingCartMapper {
    /**
     * Перевод в сущность корзины продуктов
     *
     * @param shoppingCartDto трансферная сущность - корзины продуктов
     * @return сущность корзины продуктов
     */
    public ShoppingCart toShoppingCart(ShoppingCartDto shoppingCartDto) {
        return ShoppingCart.builder()
                .id(shoppingCartDto.getId())
                .products(shoppingCartDto.getProducts())
                .build();
    }

    /**
     * Перевод в трансферную сущность корзины продуктов
     *
     * @param shoppingCart сущность - корзины продуктов
     * @return трансферная сущность корзины продуктов
     */
    public ShoppingCartDto toShoppingCartDto(ShoppingCart shoppingCart) {
        return ShoppingCartDto.builder()
                .id(shoppingCart.getId())
                .products(shoppingCart.getProducts())
                .build();
    }
}
