package ru.yandex.practicum.client;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.model.*;

import java.util.Collection;
import java.util.UUID;
import java.util.List;

/**
 * Контроллер Shopping Store
 */
@FeignClient(name = "shopping-store")
public interface ShoppingStoreClient {
    /**
     * Базовый путь (пустая строка)
     */
    String BASE_PATH = "";
    /**
     * Путь для изменения состояния товара
     */
    String REMOVE_PATH = "/removeProductFromStore";
    /**
     * Путь для изменения доступности товара
     */
    String QUANTITY_STATE_PATH = "/quantityState";

    /**
     * Получение списка товаров по типу в пагинированном виде
     *
     * @param category - Категория устройств
     * @param pageable - Пагинация
     *
     * @return ProductDto - Список товаров в пагинацией
     */
    @GetMapping(BASE_PATH)
    ProductsDto getProducts(ProductCategory category, Pageable pageable);

    /**
     * Создание нового товара в ассортименте
     *
     * @param productDto - Добавляемый товар.
     * @return ProductDto - Созданный товар с id
     */
    @PutMapping(BASE_PATH)
    ProductDto createNewProduct(@RequestBody @Valid ProductDto productDto);

    /**
     * Обновление товара в ассортименте, например уточнение описания, характеристик и т.д.
     *
     * @param productDto    обновляемый товар
     * @return  обновленный товар
     */
    @PostMapping(BASE_PATH)
    ProductDto updateProduct(@RequestBody @Valid ProductDto productDto);

    /**
     * Удалить товар из ассортимента магазина. Функция для менеджерского состава.
     *
     * @param uuid  id товара
     * @return void
     */
    @PostMapping(REMOVE_PATH)
    boolean removeProductFromStore(@RequestBody UUID uuid);

    /**
     * Запрос на изменение статуса товара в магазине, например: "Закончился", "Мало" и т.д.
     *
     * @param setProductQuantityStateRequest запрос на изменение статуса остатка товара.
     * @return Изменение ProductDto
     */
    @PostMapping(QUANTITY_STATE_PATH)
    ProductDto setProductQuantityState(SetProductQuantityStateRequest setProductQuantityStateRequest);

    /**
     * Получить сведения по товару из БД.
     *
     * @param productId     id сущности БД
     * @return Сущность DTO ProductDto
     */
    @GetMapping("/{productId}")
    ProductDto getProduct(@PathVariable UUID productId);

    /**
     * Получить коллекцию продуктов
     *
     * @param ids id продуктов
     * @return продукты
     */
    @GetMapping("/onlyIds")
    public List<ProductDto> getProductByIds(Collection<UUID> ids);
}
