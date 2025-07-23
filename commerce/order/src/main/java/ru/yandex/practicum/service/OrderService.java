package ru.yandex.practicum.service;

import ru.yandex.practicum.model.CreateNewOrderRequest;
import ru.yandex.practicum.model.OrderDto;
import ru.yandex.practicum.model.ProductReturnRequest;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    /**
     * Получить список заказов
     *
     * @param userName Имя пользователя
     * @return список заказов
     */
    List<OrderDto> getClientOrders(String userName);

    /**
     * Создание нового заказа
     *
     * @param request сущность нового заказа
     * @return трансферная сущность заказа
     */
    OrderDto createNewOrder(CreateNewOrderRequest request);

    /**
     * Возврат заказа
     *
     * @param request запрос возврата заказа
     * @return трансферная сущность заказа
     */
    OrderDto returnProducts(ProductReturnRequest request);

    /**
     * Оплата заказа
     *
     * @param orderId уникальный идентификатор корзины
     * @return трансферная сущность заказа
     */
    OrderDto payOrder(UUID orderId);

    /**
     * Доставка заказа.
     *
     * @param orderId уникальный идентификатор корзины
     * @return трансферная сущность заказа
     */
    OrderDto deliverOrder(UUID orderId);

    /**
     * Доставка заказа произошла с ошибкой.
     *
     * @param orderId уникальный идентификатор корзины
     * @return трансферная сущность заказа
     */
    OrderDto failDeliverOrder(UUID orderId);

    /**
     * Завершение заказа.
     *
     * @param orderId уникальный идентификатор корзины
     * @return трансферная сущность заказа
     */
    OrderDto completeOrder(UUID orderId);

    /**
     * Расчёт стоимости заказа.
     *
     * @param orderId уникальный идентификатор корзины
     * @return трансферная сущность заказа
     */
    OrderDto calculateTotalPrice(UUID orderId);

    /**
     * Расчёт стоимости доставки заказа.
     *
     * @param orderId уникальный идентификатор корзины
     * @return трансферная сущность заказа
     */
    OrderDto calculateDeliveryPrice(UUID orderId);

    /**
     * Сборка заказа.
     *
     * @param orderId уникальный идентификатор корзины
     * @return трансферная сущность заказа
     */
    OrderDto assemblyOrder(UUID orderId);

    /**
     * Сборка заказа произошла с ошибкой.
     *
     * @param orderId уникальный идентификатор корзины
     * @return трансферная сущность заказа
     */
    OrderDto failAssemblyOrder(UUID orderId);

    /**
     * Успешный платежный ордер
     *
     * @param orderId идентификатор корзины
     * @return трансферная сущность заказа
     */
    OrderDto successPayOrder(UUID orderId);

    /**
     * Ошибка при заказе
     *
     * @param orderId orderId идентификатор корзины
     * @return трансферная сущность заказа
     */
    OrderDto failPayOrder(UUID orderId);

    /**
     * Получить заказ
     *
     * @param orderId orderId идентификатор корзины
     * @return трансферная сущность заказа
     */

    OrderDto getOrderDetails(UUID orderId);
}
