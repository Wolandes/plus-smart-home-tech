package ru.yandex.practicum.client;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.model.CreateNewOrderRequest;
import ru.yandex.practicum.model.OrderDto;
import ru.yandex.practicum.model.ProductReturnRequest;
import ru.yandex.practicum.util.ValidationUtil;

import java.util.List;
import java.util.UUID;

@Validated
@FeignClient(name = "order", path = "/api/v1/order")
public interface OrderClient {
    /**
     * Получить список заказов
     *
     * @param userName Имя пользователя
     * @return список заказов
     */
    @GetMapping
    List<OrderDto> getClientOrders(
            @RequestParam(name = "username")
            @NotBlank(message = ValidationUtil.VALIDATION_USERNAME_MESSAGE)
            String userName);

    /**
     * Создание нового заказа
     *
     * @param request сущность нового заказа
     * @return трансферная сущность заказа
     */
    @PutMapping
    OrderDto createNewOrder(@RequestBody @Valid CreateNewOrderRequest request);

    /**
     * Возврат заказа
     *
     * @param request запрос возврата заказа
     * @return трансферная сущность заказа
     */
    @PostMapping("/return")
    OrderDto productReturn(@RequestBody @Valid ProductReturnRequest request);

    /**
     * Оплата заказа
     *
     * @param orderId уникальный идентификатор корзины
     * @return трансферная сущность заказа
     */
    @PostMapping("/payment")
    OrderDto payment(@RequestBody @NotNull UUID orderId);

    /**
     * Оплата заказа произошла с ошибкой.
     *
     * @param orderId уникальный идентификатор корзины
     * @return трансферная сущность заказа
     */
    @PostMapping("/payment/failed")
    OrderDto paymentFailed(@RequestBody @NotNull UUID orderId);

    /**
     * Доставка заказа.
     *
     * @param orderId уникальный идентификатор корзины
     * @return трансферная сущность заказа
     */
    @PostMapping("/delivery")
    OrderDto deliverOrder(@RequestBody @NotNull UUID orderId);

    /**
     * Доставка заказа произошла с ошибкой.
     *
     * @param orderId уникальный идентификатор корзины
     * @return трансферная сущность заказа
     */
    @PostMapping("/delivery/failed")
    OrderDto failDeliverOrder(@RequestBody @NotNull UUID orderId);

    /**
     * Завершение заказа.
     *
     * @param orderId уникальный идентификатор корзины
     * @return трансферная сущность заказа
     */
    @PostMapping("/completed")
    OrderDto complete(@RequestBody @NotNull UUID orderId);

    /**
     * Расчёт стоимости заказа.
     *
     * @param orderId уникальный идентификатор корзины
     * @return трансферная сущность заказа
     */
    @PostMapping("/calculate/total")
    OrderDto calculateTotalCost(@RequestBody @NotNull UUID orderId);

    /**
     * Расчёт стоимости доставки заказа.
     *
     * @param orderId уникальный идентификатор корзины
     * @return трансферная сущность заказа
     */
    @PostMapping("/calculate/delivery")
    OrderDto calculateDeliveryCost(@RequestBody @NotNull UUID orderId);

    /**
     * Сборка заказа.
     *
     * @param orderId уникальный идентификатор корзины
     * @return трансферная сущность заказа
     */
    @PostMapping("/assembly")
    OrderDto assembly(@RequestBody @NotNull UUID orderId);

    /**
     * Сборка заказа произошла с ошибкой.
     *
     * @param orderId уникальный идентификатор корзины
     * @return трансферная сущность заказа
     */
    @PostMapping("/assembly/failed")
    OrderDto assemblyFailed(@RequestBody @NotNull UUID orderId);

    /**
     * Успешный платежный ордер
     *
     * @param orderId идентификатор корзины
     * @return трансферная сущность заказа
     */
    @PostMapping("/payment/success")
    OrderDto successPayOrder(@RequestBody @NotNull UUID orderId);

    /**
     * Ошибка при заказе
     *
     * @param orderId orderId идентификатор корзины
     * @return трансферная сущность заказа
     */
    @PostMapping("/payment/failed")
    OrderDto failPayOrder(@RequestBody @NotNull UUID orderId);
}
