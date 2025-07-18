package ru.yandex.practicum.service;

import ru.yandex.practicum.model.CreateNewOrderRequest;
import ru.yandex.practicum.model.OrderDto;
import ru.yandex.practicum.model.ProductReturnRequest;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    /**
     * Получить заказы пользователя.
     *
     * @param userName имя пользователя
     * @return Список всех заказов пользователя
     */
    List<OrderDto> getClientOrders(String userName);

    /**
     * Создать новый заказ в системе.
     *
     * @param createNewOrderRequest новый заказа
     * @return Оформленный заказ пользователя
     */
    OrderDto createNewOrder(CreateNewOrderRequest createNewOrderRequest);

    /**
     * Возврат заказа.
     *
     * @param productReturnRequest Запрос на возврат заказа.
     * @return Заказ пользователя после сборки
     */
    OrderDto productReturn(ProductReturnRequest productReturnRequest);

    /**
     * Оплата заказа
     *
     * @param id уникальный идентификатор корзины
     * @return трансферная сущность заказа
     */
    OrderDto payment(UUID id);

    /**
     * Оплата заказа произошла с ошибкой.
     *
     * @param id Идентификатор заказа.
     * @return Заказ пользователя после ошибки оплаты
     */
    OrderDto paymentFailed(UUID id);

    /**
     * Заказ пользователя после доставки
     *
     * @param id Идентификатор заказа.
     * @return Заказ пользователя после доставки
     */
    OrderDto delivery(UUID id);

    /**
     * Доставка заказа произошла с ошибкой.
     *
     * @param id Идентификатор заказа.
     * @return Заказ пользователя после ошибки доставки
     */
    OrderDto deliveryFailed(UUID id);

    /**
     * Завершение заказа.
     *
     * @param id Идентификатор заказа.
     * @return Заказ пользователя после всех стадий и завершенный
     */
    OrderDto complete(UUID id);

    /**
     * Расчёт стоимости заказа.
     *
     * @param id Идентификатор заказа.
     * @return Заказ пользователя с расчётом общей стоимости
     */
    OrderDto calculateTotalCost(UUID id);

    /**
     * Расчёт стоимости доставки заказа.
     *
     * @param id Идентификатор заказа.
     * @return Заказ пользователя с расчётом доставки
     */
    OrderDto calculateDeliveryCost(UUID id);

    /**
     * Сборка заказа.
     *
     * @param id Идентификатор заказа.
     * @return Заказ пользователя после сборки
     */
    OrderDto assembly(UUID id);

    /**
     * Сборка заказа произошла с ошибкой.
     *
     * @param id Идентификатор заказа.
     * @return Заказ пользователя после ошибки сборки
     */
    OrderDto assemblyFailed(UUID id);
}
