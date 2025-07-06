package ru.yandex.practicum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.practicum.error.model.SpecifiedProductAlreadyInWarehouseException;
import ru.yandex.practicum.mapper.WarehouseProductMapper;
import ru.yandex.practicum.model.*;
import ru.yandex.practicum.repository.WarehouseProductRepository;

@Service
@RequiredArgsConstructor
public class WarehouseProductServiceImpl implements WarehouseProductService {
    private final WarehouseProductRepository repository;
    private final WarehouseProductMapper mapper;

    /**
     * Описание нового товара для обработки складом.
     *
     * @param request запрос на добавления товара
     */
    @Override
    @Transactional
    public void newProductInWarehouse(NewProductInWarehouseRequest request) {
        if (repository.existsById(request.getProductId())) {
            throw new SpecifiedProductAlreadyInWarehouseException(
                    "Товар с таким описанием зарегистрирован на складе"
            );
        }
        WarehouseProduct product = mapper.toWarehouseProduct(request);
        repository.save(product);
    }

    /**
     * Предварительно проверить что количество товаров на складе достаточно для данной корзиный продуктов.
     *
     * @param shoppingCartDto трансферная сущность корзины пользователя
     * @return Трансферные зарезервированный продукты
     */
    @Override
    @Transactional
    public BookedProductsDto checkProductQuantityEnoughForShoppingCart(ShoppingCartDto shoppingCartDto) {

    }

    /**
     * Принять товар на склад.
     *
     * @param request запрос на добавления количества товара
     */
    public void AddProductToWarehouseRequest(AddProductToWarehouseRequest request) {

    }

    /**
     * Предоставить адрес склада для расчёта доставки.
     *
     * @return трансферная сущность адресса товара
     */
    public AddressDto getWarehouseAddress() {

    }
}
