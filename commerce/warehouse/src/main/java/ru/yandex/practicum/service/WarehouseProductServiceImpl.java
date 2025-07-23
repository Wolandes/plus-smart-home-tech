package ru.yandex.practicum.service;

import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.practicum.error.NoSpecifiedProductInWarehouseException;
import ru.yandex.practicum.error.ProductInShoppingCartLowQuantityInWarehouse;
import ru.yandex.practicum.error.SpecifiedProductAlreadyInWarehouseException;
import ru.yandex.practicum.mapper.WarehouseProductMapper;
import ru.yandex.practicum.model.*;
import ru.yandex.practicum.repository.BookingRepository;
import ru.yandex.practicum.repository.WarehouseProductRepository;
import ru.yandex.practicum.util.AddressUtil;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WarehouseProductServiceImpl implements WarehouseProductService {
    private final WarehouseProductRepository repository;
    private final WarehouseProductMapper mapper;
    private final BookingRepository bookingRepository;

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
        Map<UUID, Long> cartProducts = shoppingCartDto.getProducts();
        Map<UUID, WarehouseProduct> products = repository.findAllById(cartProducts.keySet())
                .stream()
                .collect(Collectors.toMap(WarehouseProduct::getProductId, Function.identity()));
        if (products.size() != cartProducts.size()) {
            throw new ProductInShoppingCartLowQuantityInWarehouse("Некоторых товаров нет на складе");
        }
        double weight = 0;
        double volume = 0;
        boolean fragile = false;
        for (Map.Entry<UUID, Long> cartProduct : cartProducts.entrySet()) {
            WarehouseProduct product = products.get(cartProduct.getKey());
            if (cartProduct.getValue() > product.getQuantity()) {
                throw new ProductInShoppingCartLowQuantityInWarehouse(
                        "Ошибка, товар из корзины не находится в требуемом количестве на складе");
            }
            weight += product.getWeight() * cartProduct.getValue();
            volume += product.getHeight() * product.getWeight() * product.getDepth() * cartProduct.getValue();
            fragile = fragile || product.isFragile();
        }
        return new BookedProductsDto(
                weight,
                volume,
                fragile
        );
    }

    /**
     * Принять товар на склад.
     *
     * @param request запрос на добавления количества товара
     */
    public void AddProductToWarehouseRequest(AddProductToWarehouseRequest request) {
        WarehouseProduct product = getWarehouseProduct(request.getProductId());
        long newQuantity = product.getQuantity() + request.getQuantity();
        product.setQuantity(newQuantity);
        repository.save(product);
    }

    /**
     * Предоставить адрес склада для расчёта доставки.
     *
     * @return трансферная сущность адресса товара
     */
    public AddressDto getWarehouseAddress() {
        String defValue = AddressUtil.getAddress();
        return new AddressDto(
                defValue,
                defValue,
                defValue,
                defValue,
                defValue
        );

    }

    @Override
    @Transactional
    public void shippedToDelivery(DeliveryRequest request) {
        Booking booking = getBookingById(request.getOrderId());
        booking.setDeliveryId(request.getDeliveryId());
        bookingRepository.save(booking);
    }

    @Override
    @Transactional
    public void acceptReturn(Map<UUID, Integer> products) {
        Map<UUID, WarehouseProduct> warehouseProducts = getWarehouseProducts(products.keySet());
        for (Map.Entry<UUID, Integer> product : products.entrySet()) {
            WarehouseProduct warehouseProduct = warehouseProducts.get(product.getKey());
            warehouseProduct.setQuantity(warehouseProduct.getQuantity() + product.getValue());
        }
        saveWarehouseRemains(warehouseProducts.values());
    }

    @Override
    @Transactional
    public BookedProductsDto assemblyProductsForOrder(AssemblyRequest request) {
        Map<UUID, Long> orderProducts = request.getProducts();
        Map<UUID, WarehouseProduct> products = getWarehouseProducts(orderProducts.keySet());

        double weight = 0;
        double volume = 0;
        boolean fragile = false;
        for (Map.Entry<UUID, Long> cartProduct : orderProducts.entrySet()) {
            WarehouseProduct product = products.get(cartProduct.getKey());
            long newQuantity = product.getQuantity() - cartProduct.getValue();
            if (newQuantity < 0) {
                throw new ProductInShoppingCartLowQuantityInWarehouse(
                        "Ошибка, товар из корзины не находится в требуемом количестве на складе");
            }
            product.setQuantity(newQuantity);
            weight += product.getWeight() * cartProduct.getValue();
            volume += product.getHeight() * product.getWeight() * product.getDepth() * cartProduct.getValue();
            fragile = fragile || product.isFragile();
        }
        addBooking(request);
        saveWarehouseRemains(products.values());

        return new BookedProductsDto(
                weight,
                volume,
                fragile
        );
    }

    private WarehouseProduct getWarehouseProduct(UUID productId) {
        return repository.findById(productId).orElseThrow(
                () -> new NoSpecifiedProductInWarehouseException("Нет информации о товаре на складе")
        );
    }

    private Booking getBookingById(UUID orderId) {
        return bookingRepository.findById(orderId).orElseThrow(
                () -> new NotFoundException("Нет информации о бронировании товаров по заказу")
        );
    }

    void addBooking(AssemblyRequest request) {
        Booking booking = Booking.builder()
                .orderId(request.getOrderId())
                .products(request.getProducts())
                .build();
        bookingRepository.save(booking);
    }

    Map<UUID, WarehouseProduct> getWarehouseProducts(Collection<UUID> ids) {
        Map<UUID, WarehouseProduct> products = repository.findAllById(ids)
                .stream()
                .collect(Collectors.toMap(WarehouseProduct::getProductId, Function.identity()));
        if (products.size() != ids.size()) {
            throw new ProductInShoppingCartLowQuantityInWarehouse("Некоторых товаров нет на складе");
        }

        return products;
    }

    void saveWarehouseRemains(Collection<WarehouseProduct> products) {
        repository.saveAll(products);
    }
}
