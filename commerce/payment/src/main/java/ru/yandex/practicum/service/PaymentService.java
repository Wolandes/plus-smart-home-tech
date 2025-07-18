package ru.yandex.practicum.service;

import ru.yandex.practicum.model.OrderDto;
import ru.yandex.practicum.model.PaymentDto;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Сервер платежа
 */
public interface PaymentService {
    /**
     * Формирование оплаты для заказа (переход в платежный шлюз).
     *
     * @param order Заказ для формирования оплаты.
     * @return Сформированная оплата заказа (переход в платежный шлюз)
     */
    PaymentDto createPayment(OrderDto order);

    /**
     * Расчёт полной стоимости заказа.
     *
     * @param order Заказ для расчёта.
     * @return Полная стоимость заказа
     */
    BigDecimal getTotalCost(OrderDto order);

    /**
     * Метод для эмуляции успешной оплаты в платежного шлюза.
     *
     * @param orderId Идентификатор платежа.
     */
    void paymentSuccess(UUID orderId);

    /**
     * Расчёт стоимости товаров в заказе.
     *
     * @param order Заказ для расчёта.
     * @return Расчёт стоимости товаров в заказе
     */
    BigDecimal getProductCost(OrderDto order);

    /**
     * Метод для эмуляции отказа в оплате платежного шлюза.
     *
     * @param orderId Идентификатор платежа.
     */
    void paymentFailed(UUID orderId);
}
