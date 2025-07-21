package ru.yandex.practicum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.client.DeliveryClient;
import ru.yandex.practicum.client.PaymentClient;
import ru.yandex.practicum.client.ShoppingCartClient;
import ru.yandex.practicum.client.WarehouseClient;
import ru.yandex.practicum.mapper.OrderMapper;
import ru.yandex.practicum.model.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Реализация сервиса заказа
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final InteriorOrderService interiorOrderService;
    private final OrderMapper orderMapper;

    private final ShoppingCartClient shoppingCartClient;
    private final PaymentClient paymentClient;
    private final DeliveryClient deliveryClient;
    private final WarehouseClient warehouseClient;

    @Override
    public List<OrderDto> getClientOrders(String userName) {
        log.info("get orders for user {}", userName);
        return orderMapper.orderDtoList(interiorOrderService.getClientOrders(userName));
    }

    @Override
    public OrderDto createNewOrder(CreateNewOrderRequest request) {
        Order order = interiorOrderService.createNewOrder(getNewOrderFromRequest(request));
        log.info("new order from request: {}", order);

        UUID deliveryId = getNewDeliveryId(order.getOrderId(), request.getDeliveryAddress());
        return orderMapper.toOrderDto(interiorOrderService.setDelivery(order.getOrderId(), deliveryId));
    }

    @Override
    public OrderDto returnProducts(ProductReturnRequest request) {
        warehouseClient.acceptReturn(request.getProducts());

        return orderMapper.toOrderDto(interiorOrderService.returnProducts(request.getOrderId()));
    }

    @Override
    public OrderDto payOrder(UUID orderId) {
        Order order = getOrderById(orderId);
        BigDecimal productCost = paymentClient.getProductCost(orderMapper.toOrderDto(order));
        BigDecimal deliveryCost = deliveryClient.calculateDeliveryCost(orderMapper.toOrderDto(order));
        order.setDeliveryPrice(deliveryCost);
        order.setProductPrice(productCost);
        log.info("order after setting productPrice: {}", order);
        BigDecimal totalCost = paymentClient.getTotalCost(orderMapper.toOrderDto(order));
        order.setTotalPrice(totalCost);
        PaymentDto paymentDto = paymentClient.createPayment(orderMapper.toOrderDto(order));
        order.setPaymentId(paymentDto.getPaymentId());

        Order savedOrder = interiorOrderService.savePaymentInfo(order);
        log.info("payOrder: order after creating payment {}", savedOrder);
        return orderMapper.toOrderDto(savedOrder);
    }

    @Override
    public OrderDto successPayOrder(UUID orderId) {
        return orderMapper.toOrderDto(interiorOrderService.successPayOrder(orderId));
    }

    @Override
    public OrderDto failPayOrder(UUID orderId) {
        return orderMapper.toOrderDto(interiorOrderService.failPayOrder(orderId));
    }

    @Override
    public OrderDto deliverOrder(UUID orderId) {
        return orderMapper.toOrderDto(interiorOrderService.deliverOrder(orderId));
    }

    @Override
    public OrderDto failDeliverOrder(UUID orderId) {
        return orderMapper.toOrderDto(interiorOrderService.failDeliverOrder(orderId));
    }

    @Override
    public OrderDto completeOrder(UUID orderId) {
        return orderMapper.toOrderDto(interiorOrderService.completeOrder(orderId));
    }

    @Override
    public OrderDto calculateTotalPrice(UUID orderId) {
        Order order = getOrderById(orderId);
        BigDecimal totalCost = paymentClient.getTotalCost(orderMapper.toOrderDto(order));

        return orderMapper.toOrderDto(interiorOrderService.setTotalPrice(orderId, totalCost));
    }

    @Override
    public OrderDto calculateDeliveryPrice(UUID orderId) {
        Order order = getOrderById(orderId);
        BigDecimal deliveryCost = deliveryClient.calculateDeliveryCost(orderMapper.toOrderDto(order));

        return orderMapper.toOrderDto(interiorOrderService.setDeliveryPrice(orderId, deliveryCost));
    }

    @Override
    public OrderDto assemblyOrder(UUID orderId) {
        warehouseClient.assemblyProductsForOrder(getNewAssemblyProductsForOrderRequest(orderId));

        return orderMapper.toOrderDto(interiorOrderService.assemblyOrder(orderId));
    }

    @Override
    public OrderDto failAssemblyOrder(UUID orderId) {
        return orderMapper.toOrderDto(interiorOrderService.failAssemblyOrder(orderId));
    }

    @Override
    public OrderDto getOrderDetails(UUID orderId) {
        return orderMapper.toOrderDto(getOrderById(orderId));
    }

    private AssemblyRequest getNewAssemblyProductsForOrderRequest(UUID orderId) {
        Order order = getOrderById(orderId);
        return AssemblyRequest.builder()
                .orderId(orderId)
                .products(order.getProducts())
                .build();
    }

    private Order getNewOrderFromRequest(CreateNewOrderRequest request) {
        BookedProductsDto bookedProductsDto = shoppingCartClient.bookProducts(request.getUserName());
        return Order.builder()
                .userName(request.getUserName())
                .cartId(request.getShoppingCart().getId())
                .products(request.getShoppingCart().getProducts())
                .deliveryWeight(bookedProductsDto.getDeliveryWeight())
                .deliveryVolume(bookedProductsDto.getDeliveryVolume())
                .fragile(bookedProductsDto.getFragile())
                .state(OrderStatus.NEW)
                .build();

    }

    private UUID getNewDeliveryId(UUID orderId, AddressDto deliveryAddress) {
        DeliveryDto deliveryDto = DeliveryDto.builder()
                .senderAddress(warehouseClient.getWarehouseAddress())
                .recipientAddress(deliveryAddress)
                .orderId(orderId)
                .deliveryState(DeliveryState.CANCELLED.CREATED)
                .build();

        log.info("Creating DeliveryDto: {}", deliveryDto);
        return deliveryClient.createDelivery(deliveryDto).getDeliveryId();
    }

    private Order getOrderById(UUID orderId) {
        return interiorOrderService.getOrderById(orderId);
    }

}
