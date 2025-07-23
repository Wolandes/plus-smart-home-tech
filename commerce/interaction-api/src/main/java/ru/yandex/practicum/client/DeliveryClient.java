package ru.yandex.practicum.client;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.model.DeliveryDto;
import ru.yandex.practicum.model.OrderDto;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Feign Контроллер доставки
 */
@Validated
@FeignClient(name = "delivery", path = "/api/v1/delivery")
public interface DeliveryClient {

    /**
     * Создать новую доставку в БД.
     *
     * @param deliveryDto Доставка для сохранения.
     * @return Указанная заявка с присвоенным идентификатором
     */
    @PutMapping
    DeliveryDto createDelivery(@RequestBody @Valid DeliveryDto deliveryDto);

    /**
     * Эмуляция успешной доставки товара.
     *
     * @param orderId Идентификатор заказа.
     */
    @PostMapping("/successful")
    void completeDelivery(@RequestBody @NotNull UUID orderId);

    /**
     * Эмуляция получения товара в доставку.
     *
     * @param orderId Идентификатор заказа.
     */
    @PostMapping("/picked")
    void confirmPickup(@RequestBody @NotNull UUID orderId);

    /**
     * Эмуляция неудачного вручения товара.
     *
     * @param orderId Идентификатор заказа.
     */
    @PostMapping("/failed")
    void failDelivery(@RequestBody @NotNull UUID orderId);

    /**
     * Расчёт полной стоимости доставки заказа.
     *
     * @param orderDto Заказ для расчёта.
     * @return Полная стоимость доставки заказа
     */
    @PostMapping("/cost")
    BigDecimal calculateDeliveryCost(@RequestBody @Valid OrderDto orderDto);
}
