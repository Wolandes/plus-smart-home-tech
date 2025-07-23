package ru.yandex.practicum.mapper;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.model.Order;
import ru.yandex.practicum.model.OrderDto;

import java.util.*;

/**
 * Маппер заказа
 */
@Component
public class OrderMapper {

    /**
     * Перевод в трансферную сущность заказа
     *
     * @param order сущность заказа
     * @return трансферная сущность заказа
     */
    public OrderDto toOrderDto(Order order) {
        Map<UUID, Long> productsMain = order.getProducts();
        Map<UUID, Integer> productsDto = new HashMap<>();
        for (UUID uuid : productsMain.keySet()) {
            Integer i = Math.toIntExact(productsMain.get(uuid));
            productsDto.put(uuid, i);
        }
        return OrderDto.builder()
                .orderId(order.getOrderId())
                .shoppingCartId(order.getCartId())
                .products(productsDto)
                .paymentId(order.getPaymentId())
                .deliveryId(order.getDeliveryId())
                .state(order.getState())
                .deliveryWeight(order.getDeliveryWeight())
                .deliveryVolume(order.getDeliveryVolume())
                .fragile(order.isFragile())
                .totalPrice(order.getTotalPrice())
                .deliveryPrice(order.getDeliveryPrice())
                .productPrice(order.getProductPrice())
                .build();
    }

    /**
     * Перевод в сущность заказа
     *
     * @param orderDto трансферная сущность заказа
     * @return в сущность заказа
     */
    public Order toOrder(OrderDto orderDto) {
        Map<UUID, Integer> productsDto = orderDto.getProducts();
        Map<UUID, Long> productsIntermediate = new HashMap<>();
        for (UUID uuid : productsDto.keySet()) {
            Integer i = productsDto.get(uuid);
            productsIntermediate.put(uuid, Long.valueOf(i));
        }
        return Order.builder()
                .orderId(orderDto.getOrderId())
                .cartId(orderDto.getShoppingCartId())
                .products(productsIntermediate)
                .paymentId(orderDto.getPaymentId())
                .deliveryId(orderDto.getDeliveryId())
                .state(orderDto.getState())
                .deliveryWeight(orderDto.getDeliveryWeight())
                .deliveryVolume(orderDto.getDeliveryVolume())
                .fragile(orderDto.isFragile())
                .totalPrice(orderDto.getTotalPrice())
                .deliveryPrice(orderDto.getDeliveryPrice())
                .productPrice(orderDto.getProductPrice())
                .build();
    }

    /**
     * Перевод в OrderListDto
     *
     * @param orderList Колллекция сущности заказа
     * @return траснферную колллекцию сущности заказа
     */
    public List<OrderDto> orderDtoList(List<Order> orderList) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (Order order : orderList) {
            OrderDto orderDto = toOrderDto(order);
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }
}
