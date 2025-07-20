package ru.yandex.practicum.service;

import ru.yandex.practicum.model.*;

/**
 * Сервис склада
 */
public interface WarehouseProductService {
    /**
     * Описание нового товара для обработки складом.
     *
     * @param request запрос на добавления товара
     */
    void newProductInWarehouse(NewProductInWarehouseRequest request);

    /**
     * Предварительно проверить что количество товаров на складе достаточно для данной корзиный продуктов.
     *
     * @param shoppingCartDto трансферная сущность корзины пользователя
     * @return Трансферные зарезервированный продукты
     */
    BookedProductsDto checkProductQuantityEnoughForShoppingCart(ShoppingCartDto shoppingCartDto);

    /**
     * Принять товар на склад.
     *
     * @param request запрос на добавления количества товара
     */
    void AddProductToWarehouseRequest(AddProductToWarehouseRequest request);

    /**
     * Предоставить адрес склада для расчёта доставки.
     *
     * @return трансферная сущность адресса товара
     */
    AddressDto getWarehouseAddress();

    /**
     * Запрос на доставку
     *
     * @param request запрос на доставку
     */
    void shippedToDelivery(DeliveryRequest request);
}
