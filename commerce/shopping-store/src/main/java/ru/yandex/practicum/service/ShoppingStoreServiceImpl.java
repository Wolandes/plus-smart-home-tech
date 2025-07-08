package ru.yandex.practicum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.practicum.error.NotFoundException;
import ru.yandex.practicum.mapper.ProductMapper;
import ru.yandex.practicum.model.*;
import ru.yandex.practicum.repository.ProductRepository;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Сервис для сущности "Продукт".
 */
@Service
@RequiredArgsConstructor
public class ShoppingStoreServiceImpl implements ShoppingStoreService {
    /**
     * Хранилище данных для сущности "Продукт".
     */
    private final ProductRepository repository;

    /**
     * Маппер для сущности "Продукт".
     */
    private final ProductMapper mapper;

    /**
     * Получение списка товаров по типу в пагинированном виде
     *
     * @param category - Категория устройств
     * @param pageable - Пагинация
     * @return ProductDto - Список товаров в пагинацией
     */
    @Override
    public ProductsDto getProducts(ProductCategory category, Pageable pageable) {
        PageRequest pageRequest = pageable.toPageRequest();
        List<Product> products = repository.findAllByProductCategory(category, pageRequest);
        List<ProductDto> productDtos = mapper.toListProductDto(products);
        ProductsDto productsDto = new ProductsDto();
        productsDto.setContent(productDtos);
        List<SortInfo> sortInfoList = pageRequest.getSort().stream()
                .map(order -> new SortInfo(order.getProperty(), order.getDirection().name()))
                .collect(Collectors.toList());
        productsDto.setSort(sortInfoList);

        return productsDto;
    }

    /**
     * Создание нового товара в ассортименте
     *
     * @param productDto - Добавляемый товар.
     * @return ProductDto - Созданный товар с id
     */
    @Override
    @Transactional
    public ProductDto createNewProduct(ProductDto productDto) {
        Product product = mapper.toProduct(productDto);

        return mapper.toProductDto(repository.save(product));
    }

    /**
     * Обновление товара в ассортименте, например уточнение описания, характеристик и т.д.
     *
     * @param productDto обновляемый товар
     * @return обновленный товар
     */
    @Override
    @Transactional
    public ProductDto updateProduct(ProductDto productDto) {
        Product product = repository.findById(productDto.getProductId()).orElseThrow(() -> new NotFoundException("Нет найден продукт с id: "+ productDto.getProductId()) );
        Product newProduct = repository.save(mapper.toProduct(productDto));
        return mapper.toProductDto(newProduct);
    }

    /**
     * Удалить товар из ассортимента магазина. Функция для менеджерского состава.
     *
     * @param uuid  id товара
     * @return void
     */
    @Override
    @Transactional
    public boolean removeProductFromStore(UUID uuid){
        Product product = repository.findById(uuid).orElseThrow(() -> new NotFoundException("Нет найден продукт с id: "+ uuid) );
        product.setProductState(ProductState.DEACTIVATE);
        repository.save(product);
        return true;
    }

    /**
     * Запрос на изменение статуса товара в магазине, например: "Закончился", "Мало" и т.д.
     *
     * @param setProductQuantityStateRequest запрос на изменение статуса остатка товара.
     * @return Изменение ProductDto
     */
    @Override
    @Transactional
    public ProductDto setProductQuantityState(SetProductQuantityStateRequest setProductQuantityStateRequest){
        Product product = repository.findById(setProductQuantityStateRequest.getProductId()).orElseThrow(() -> new NotFoundException("Нет найден продукт с id: "+ setProductQuantityStateRequest.getProductId()) );
        product.setQuantityState(setProductQuantityStateRequest.getQuantityState());
        repository.save(product);
        return mapper.toProductDto(product);
    }

    /**
     * Получить сведения по товару из БД.
     *
     * @param productId     id сущности БД
     * @return Сущность DTO ProductDto
     */
    @Override
    @Transactional
    public ProductDto getProduct(UUID productId){
        Product product = repository.findById(productId).orElseThrow(() -> new NotFoundException("Нет найден продукт с id: "+ productId) );
        return mapper.toProductDto(product);
    }
}
