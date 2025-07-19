package ru.yandex.practicum.service;

import ru.yandex.practicum.model.DeliveryDto;
import ru.yandex.practicum.model.OrderDto;

import java.math.BigDecimal;
import java.util.UUID;

public interface DeliveryService {

    /**
     * Создать новую доставку в БД.
     *
     * @param deliveryDto Доставка для сохранения.
     * @return Указанная заявка с присвоенным идентификатором
     */
    DeliveryDto createDelivery(DeliveryDto deliveryDto);

    /**
     * Эмуляция успешной доставки товара.
     *
     * @param orderId Идентификатор заказа.
     */
    void completeDelivery(UUID orderId);

    /**
     * Эмуляция получения товара в доставку.
     *
     * @param orderId Идентификатор заказа.
     */
    void confirmPickup(UUID orderId);

    /**
     * Эмуляция неудачного вручения товара.
     *
     * @param orderId Идентификатор заказа.
     */
    void failDelivery(UUID orderId);

    /**
     * Расчёт полной стоимости доставки заказа.
     *
     * @param orderDto Заказ для расчёта.
     * @return Полная стоимость доставки заказа
     */
    BigDecimal calculateDeliveryCost(OrderDto orderDto);
}
