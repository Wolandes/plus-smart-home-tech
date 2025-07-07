package ru.yandex.practicum.client;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.model.*;

/**
 * Трансферный клиент склада
 */
@FeignClient(name = "warehouse")
public interface WarehouseClient {
    /**
     * Путь проверки
     */
    String CHECK_ENDPOINT = "/api/v1/warehouse/check";

    /**
     * Путь добавления
     */
    String ADD_ENDPOINT = "/add";

    /**
     * Путь адреса
     */
    String ADDRESS_ENDPOINT = "/address";

    /**
     * Описание нового товара для обработки складом.
     *
     * @param request запрос на добавления товара
     */
    @PutMapping
    void newProductInWarehouse(@RequestBody @Valid NewProductInWarehouseRequest request);

    /**
     * Предварительно проверить что количество товаров на складе достаточно для данной корзиный продуктов.
     *
     * @param shoppingCartDto трансферная сущность корзины пользователя
     * @return Трансферные зарезервированный продукты
     */
    @PostMapping(CHECK_ENDPOINT)
    BookedProductsDto checkProductQuantityEnoughForShoppingCart(@RequestBody @Valid ShoppingCartDto shoppingCartDto);

    /**
     * Принять товар на склад.
     *
     * @param request запрос на добавления количества товара
     */
    @PostMapping(ADD_ENDPOINT)
    void AddProductToWarehouseRequest(@RequestBody @Valid AddProductToWarehouseRequest request);

    /**
     * Предоставить адрес склада для расчёта доставки.
     *
     * @return трансферная сущность адресса товара
     */
    @GetMapping(ADDRESS_ENDPOINT)
    AddressDto getWarehouseAddress();
}
