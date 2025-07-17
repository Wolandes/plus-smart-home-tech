package ru.yandex.practicum.client;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.model.CreateNewOrderRequest;
import ru.yandex.practicum.model.OrderDto;
import ru.yandex.practicum.model.ProductReturnRequest;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "order")
public interface OrderClient {
    /**
     * Получить список заказов
     *
     * @param username Имя пользователя
     * @return список заказов
     */
    @GetMapping("/api/v1/order")
    List<OrderDto> getOrdersUser(@RequestParam String username);

    /**
     * Создание нового заказа
     *
     * @param request сущность нового заказа
     * @return трансферная сущность заказа
     */
    @PutMapping("/api/v1/order")
    OrderDto createOrder(@Valid @RequestBody CreateNewOrderRequest request);

    /**
     * Возврат заказа
     *
     * @param request запрос возврата заказа
     * @return трансферная сущность заказа
     */
    @PostMapping("/api/v1/order/return")
    OrderDto returnOrder(@RequestBody @Valid ProductReturnRequest request);

    /**
     * Оплата заказа
     *
     * @param orderId уникальный идентификатор корзины
     * @return трансферная сущность заказа
     */
    @PostMapping("/api/v1/order/payment")
    OrderDto paymentOrder(@RequestBody UUID orderId);

    /**
     * Оплата заказа произошла с ошибкой.
     *
     * @param orderId уникальный идентификатор корзины
     * @return трансферная сущность заказа
     */
    @PostMapping("/api/v1/order/failed")
    OrderDto failedPaymentOrder(@RequestBody UUID orderId);

    /**
     * Доставка заказа.
     *
     * @param orderId уникальный идентификатор корзины
     * @return трансферная сущность заказа
     */
    @PostMapping("/api/v1/order/delivery")
    OrderDto deliveryOrder(@RequestBody UUID orderId);

    /**
     * Доставка заказа произошла с ошибкой.
     *
     * @param orderId уникальный идентификатор корзины
     * @return трансферная сущность заказа
     */
    @PostMapping("/api/v1/order/delivery/failed")
    OrderDto deliveryOrderFailed(@RequestBody UUID orderId);

    /**
     * Завершение заказа.
     *
     * @param orderId уникальный идентификатор корзины
     * @return трансферная сущность заказа
     */
    @PostMapping("/api/v1/order/completed")
    OrderDto completeOrder(@RequestBody UUID orderId);

    /**
     * Расчёт стоимости заказа.
     *
     * @param orderId уникальный идентификатор корзины
     * @return трансферная сущность заказа
     */
    @PostMapping("/api/v1/order/calculate/total")
    OrderDto calculateTotalCost(@RequestBody UUID orderId);

    /**
     * Расчёт стоимости доставки заказа.
     *
     * @param orderId уникальный идентификатор корзины
     * @return трансферная сущность заказа
     */
    @PostMapping("/api/v1/order/calculate/delivery")
    OrderDto calculateDeliveryCost(@RequestBody UUID orderId);

    /**
     * Сборка заказа.
     *
     * @param orderId уникальный идентификатор корзины
     * @return трансферная сущность заказа
     */
    @PostMapping("/api/v1/order/assembly")
    OrderDto assembly(@RequestBody UUID orderId);

    /**
     * Сборка заказа произошла с ошибкой.
     *
     * @param orderId уникальный идентификатор корзины
     * @return трансферная сущность заказа
     */
    @PostMapping("/api/v1/order/assembly/failed")
    OrderDto assemblyFailed(@RequestBody UUID orderId);
}
