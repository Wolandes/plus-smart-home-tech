package ru.yandex.practicum.mapper;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.model.Product;
import ru.yandex.practicum.model.ProductDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper для продукта
 */
@Component
public class ProductMapper {
    /**
     * Перевод в Product
     *
     * @param productDto сущность из interaction-api
     * @return сущность продукта
     */
    public Product toProduct(ProductDto productDto) {
        return Product.builder()
                .productId(productDto.getProductId())
                .productName(productDto.getProductName())
                .description(productDto.getDescription())
                .imageSrc(productDto.getImageSrc())
                .quantityState(productDto.getQuantityState())
                .productState(productDto.getProductState())
                .productCategory(productDto.getProductCategory())
                .price(productDto.getPrice())
                .build();
    }

    /**
     * Перевод в ProductDto
     *
     * @param product сущность продукта
     * @return  Сущность DTO продукта - productDto
     */
    public ProductDto toProductDto(Product product) {
        return ProductDto.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .imageSrc(product.getImageSrc())
                .quantityState(product.getQuantityState())
                .productState(product.getProductState())
                .productCategory(product.getProductCategory())
                .price(product.getPrice())
                .build();

    }

    /**
     * Перевод в List<ProductsDto>
     *
     * @param productList список сущности продукта
     * @return возвращает трансферный список
     */
    public List<ProductDto> toListProductDto(List<Product> productList){
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : productList) {
            ProductDto productDto = toProductDto(product);
            productDtos.add(productDto);
        }
        return productDtos;
    }
}
