package ru.yandex.practicum.client;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.model.*;
import ru.yandex.practicum.service.ShoppingStoreService;

import java.util.Collection;
import java.util.UUID;
import java.util.List;

/**
 * Контроллер хранилище Shopping Store
 */
@RestController
@RequestMapping("/api/v1/shopping-store")
@RequiredArgsConstructor
@Validated
@Slf4j
public class ShoppingStoreController implements ShoppingStoreClient {
    private final ShoppingStoreService service;

    /**
     * Получение списка товаров по типу в пагинированном виде
     *
     * @param category - Категория устройств
     * @param pageable - Пагинация
     *
     * @return ProductDto - Список товаров в пагинацией
     */
    @GetMapping
    public ProductsDto getProducts(ProductCategory category, Pageable pageable) {
        return service.getProducts(category,pageable);
    }

    /**
     * Создание нового товара в ассортименте
     *
     * @param productDto - Добавляемый товар.
     * @return ProductDto - Созданный товар с id
     */
    @PutMapping
    public ProductDto createNewProduct(@RequestBody @Valid ProductDto productDto){
        log.info("Пошел добавление нового продукта");
        return service.createNewProduct(productDto);
    }

    /**
     * Обновление товара в ассортименте, например уточнение описания, характеристик и т.д.
     *
     * @param productDto    обновляемый товар
     * @return  обновленный товар
     */
    @PostMapping
    public ProductDto updateProduct(@RequestBody @Valid ProductDto productDto){
        return service.updateProduct(productDto);
    }

    /**
     * Удалить товар из ассортимента магазина. Функция для менеджерского состава.
     *
     * @param uuid  id товара
     * @return void
     */
    @Override
    public boolean removeProductFromStore(UUID uuid){
        log.info("Пошел процесс удаления товара с id: " + uuid);
        return service.removeProductFromStore(uuid);
    }

    /**
     * Запрос на изменение статуса товара в магазине, например: "Закончился", "Мало" и т.д.
     *
     * @param setProductQuantityStateRequest запрос на изменение статуса остатка товара.
     * @return Изменение ProductDto
     */
    @PostMapping("/quantityState")
    public ProductDto setProductQuantityState(SetProductQuantityStateRequest setProductQuantityStateRequest) {
        return service.setProductQuantityState(setProductQuantityStateRequest);
    }

    /**
     * Получить сведения по товару из БД.
     *
     * @param productId     id сущности БД
     * @return Сущность DTO ProductDto
     */
    @GetMapping("/{productId}")
    public ProductDto getProduct(@PathVariable UUID productId){
        return service.getProduct(productId);
    }

    @GetMapping("/onlyIds")
    public List<ProductDto> getProductByIds(Collection<UUID> ids){
        return service.getProductByIds(ids);
    }

}
