package ru.yandex.practicum.service;

import ru.yandex.practicum.model.BookedProductsDto;
import ru.yandex.practicum.model.ChangeProductQuantityRequest;
import ru.yandex.practicum.model.ShoppingCartDto;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Контракт для сущности корзины пользователя
 */
public interface ShoppingCartService {
    /**
     * Получить актуальную корзину для авторизованного пользователя.
     *
     * @param userName имя пользователя
     * @return трансферная сущность корзины пользователя
     */
    ShoppingCartDto getShoppingCart(String userName);

    /**
     * Добавить товар в корзину.
     *
     * @param userName имя пользователя
     * @param products количество определенного продукта
     * @return транферная сущность корзины пользователя
     */
    ShoppingCartDto addProductToShoppingCart(String userName, Map<UUID, Long> products);

    /**
     * Деактивация корзины товаров для пользователя.
     *
     * @param userName имя пользователя
     */
    void deactivateCurrentShoppingCart(String userName);

    /**
     * Удалить указанные товары из корзины пользователя.
     *
     * @param userName имя пользователя
     * @param products Список идентификаторов товаров, которые нужно удалить.
     * @return транферная сущность корзины пользователя
     */
    ShoppingCartDto removeFromShoppingCart(String userName, List<UUID> products);

    /**
     * Изменить количество товаров в корзине.
     *
     * @param userName                     имя пользователя
     * @param changeProductQuantityRequest изменение количество товаров
     * @return транферная сущность корзины пользователя
     */
    ShoppingCartDto changeProductQuantity(String userName, ChangeProductQuantityRequest changeProductQuantityRequest);

    BookedProductsDto bookProducts(String userName);
}
