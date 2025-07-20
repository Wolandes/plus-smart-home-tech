package ru.yandex.practicum.client;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.model.*;
import ru.yandex.practicum.service.WarehouseProductService;

@RestController
@RequestMapping("/api/v1/warehouse")
@RequiredArgsConstructor
@Validated
public class WarehouseController implements WarehouseClient {
    private final WarehouseProductService service;

    /**
     * Описание нового товара для обработки складом.
     *
     * @param request запрос на добавления товара
     */
    @PutMapping
    public void newProductInWarehouse(@RequestBody @Valid NewProductInWarehouseRequest request){
        service.newProductInWarehouse(request);
    }

    /**
     * Предварительно проверить что количество товаров на складе достаточно для данной корзиный продуктов.
     *
     * @param shoppingCartDto трансферная сущность корзины пользователя
     * @return Трансферные зарезервированный продукты
     */
    @PostMapping(CHECK_ENDPOINT)
    public BookedProductsDto checkProductQuantityEnoughForShoppingCart(@RequestBody @Valid ShoppingCartDto shoppingCartDto){
        return service.checkProductQuantityEnoughForShoppingCart(shoppingCartDto);
    }

    /**
     * Принять товар на склад.
     *
     * @param request запрос на добавления количества товара
     */
    @PostMapping(ADD_ENDPOINT)
    public void AddProductToWarehouseRequest(@RequestBody @Valid AddProductToWarehouseRequest request){
        service.AddProductToWarehouseRequest(request);
    }

    /**
     * Предоставить адрес склада для расчёта доставки.
     *
     * @return трансферная сущность адресса товара
     */
    @GetMapping(ADDRESS_ENDPOINT)
    public AddressDto getWarehouseAddress(){
        return service.getWarehouseAddress();
    }

    @Override
    @PostMapping("/shipped")
    public void shippedToDelivery(@RequestBody @Valid DeliveryRequest request) {
        service.shippedToDelivery(request);
    }
}
