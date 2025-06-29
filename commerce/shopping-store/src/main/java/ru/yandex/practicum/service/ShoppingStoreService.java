package ru.yandex.practicum.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.yandex.practicum.model.ProductCategory;
import ru.yandex.practicum.model.ProductDto;
import ru.yandex.practicum.model.QuantityState;

import java.util.UUID;

/**
 * Контракт сервиса для сущности "Продукта".
 */
public interface ShoppingStoreService {
    /**
     * Получение списка товаров по типу в пагинированном виде
     *
     * @param category - Категория устройств
     * @param pageable - Пагинация
     *
     * @return ProductDto - Список товаров в пагинацией
     */
    Page<ProductDto> getProducts(ProductCategory category, Pageable pageable);

    /**
     * Создание нового товара в ассортименте
     *
     * @param productDto - Добавляемый товар.
     * @return ProductDto - Созданный товар с id
     */
    ProductDto createNewProduct(ProductDto productDto);

    /**
     * Обновление товара в ассортименте, например уточнение описания, характеристик и т.д.
     *
     * @param productDto    обновляемый товар
     * @return  обновленный товар
     */
    ProductDto updateProduct(ProductDto productDto);

    /**
     * Удалить товар из ассортимента магазина. Функция для менеджерского состава.
     *
     * @param uuid  id товара
     * @return void
     */
    boolean removeProductFromStore(UUID uuid);

    /**
     * Запрос на изменение статуса товара в магазине, например: "Закончился", "Мало" и т.д.
     *
     * @param productId id "Продукта".
     * @param quantityState Доступность товара
     * @return Изменение ProductDto
     */
    ProductDto setProductQuantityState(UUID productId, QuantityState quantityState);

    /**
     * Получить сведения по товару из БД.
     *
     * @param productId     id сущности БД
     * @return Сущность DTO ProductDto
     */
    ProductDto getProduct(UUID productId);

}
