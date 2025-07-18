package ru.yandex.practicum.client;

import jakarta.validation.constraints.NotNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.model.OrderDto;
import ru.yandex.practicum.model.PaymentDto;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Контроллер Payment
 */
@Validated
@FeignClient(name = "payment", path = "/api/v1/payment")
public interface PaymentClient {
    /**
     * Формирование оплаты для заказа (переход в платежный шлюз).
     *
     * @param order Заказ для формирования оплаты.
     * @return Сформированная оплата заказа (переход в платежный шлюз)
     */
    @PostMapping
    PaymentDto createPayment(@RequestBody @NotNull OrderDto order);

    /**
     * Расчёт полной стоимости заказа.
     *
     * @param order Заказ для расчёта.
     * @return Полная стоимость заказа
     */
    @PostMapping("/totalCost")
    BigDecimal getTotalCost(@RequestBody @NotNull OrderDto order);

    /**
     * Метод для эмуляции успешной оплаты в платежного шлюза.
     *
     * @param orderId Идентификатор платежа.
     */
    @PostMapping("/refund")
    void paymentSuccess(@RequestBody @NotNull UUID orderId);

    /**
     * Расчёт стоимости товаров в заказе.
     *
     * @param order Заказ для расчёта.
     * @return Расчёт стоимости товаров в заказе
     */
    @PostMapping("/productCost")
    BigDecimal getProductCost(@RequestBody @NotNull OrderDto order);

    /**
     * Метод для эмуляции отказа в оплате платежного шлюза.
     *
     * @param orderId Идентификатор платежа.
     */
    @PostMapping("/failed")
    void paymentFailed(@RequestBody @NotNull UUID orderId);
}
