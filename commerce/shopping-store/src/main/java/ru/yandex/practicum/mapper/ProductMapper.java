package ru.yandex.practicum.mapper;

import ru.yandex.practicum.model.Product;
import ru.yandex.practicum.model.ProductDto;

/**
 * Mapper для продукта
 */
public class ProductMapper {
    /**
     * Перевод в Product
     *
     * @Params: productDto сущность из interaction-api
     * @Returns: Product сущность продукта
     */
    public Product toProduct(ProductDto productDto){
        return new Product(
                productDto.getProductId(),
                productDto.getProductName(),
                productDto.getDescription(),
                productDto.getImageSrc(),
                productDto.getQuantityState(),
                productDto.getProductState(),
                productDto.getProductCategory(),
                productDto.getPrice()
        );
    }

    /**
     * Перевод в ProductDto
     *
     * @Params: Сущность продукта - product
     * @Returns: Сущность DTO продукта - productDto
     */
    public ProductDto toProductDto(Product product){
        return new ProductDto(
                product.getProductId(),
                product.getProductName(),
                product.getDescription(),
                product.getImageSrc(),
                product.getQuantityState(),
                product.getProductState(),
                product.getProductCategory(),
                product.getPrice()
        );
    }
}
