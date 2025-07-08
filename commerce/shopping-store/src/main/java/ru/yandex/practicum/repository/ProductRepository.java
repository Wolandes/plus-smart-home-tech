package ru.yandex.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.model.Product;
import ru.yandex.practicum.model.ProductCategory;

import java.util.List;
import java.util.UUID;

/**
 * Контракт хранилища данных для сущности "Продукт".
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    /**
     * Получить страницу товаров по категории.
     *
     * @param category категория товара
     * @param pageable параметры пагинации
     * @return страница с товарами данной категории
     */
    List<Product> findAllByProductCategory(ProductCategory category, Pageable pageable);

    /**
     * Получить Продукт по имени товара
     *
     * @param productName   имя товара
     * @return Продукт товара
     */
    Product findByProductName(String productName);
}
