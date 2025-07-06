package ru.yandex.practicum.mapper;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.model.NewProductInWarehouseRequest;
import ru.yandex.practicum.model.WarehouseProduct;

/**
 * Маппер сущности WarehouseProduct
 */
@Component
public class WarehouseProductMapper {

    /**
     * Перевод в сущность продукта
     *
     * @param warehouseProductDto трансферная сущнсо продукта
     * @return сущность продукта
     */
    public WarehouseProduct toWarehouseProduct(NewProductInWarehouseRequest warehouseProductDto){
        return WarehouseProduct.builder()
                .productId(warehouseProductDto.getProductId())
                .fragile(warehouseProductDto.isFragile())
                .width(warehouseProductDto.getDimension().getWidth())
                .height(warehouseProductDto.getDimension().getHeight())
                .depth(warehouseProductDto.getDimension().getDepth())
                .weight(warehouseProductDto.getWeight())
                .build();
    }
}
