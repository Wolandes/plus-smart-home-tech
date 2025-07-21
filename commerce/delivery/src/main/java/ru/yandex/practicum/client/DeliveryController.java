package ru.yandex.practicum.client;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.model.DeliveryDto;
import ru.yandex.practicum.model.OrderDto;
import ru.yandex.practicum.service.DeliveryService;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Реализация контроллера доставки
 */
@Validated
@RestController
@RequestMapping("/api/v1/delivery")
@RequiredArgsConstructor
public class DeliveryController implements DeliveryClient {
    private final DeliveryService deliveryService;

    @Override
    @PutMapping
    public DeliveryDto createDelivery(DeliveryDto deliveryDto) {
        return deliveryService.createDelivery(deliveryDto);
    }

    @Override
    @PostMapping("/successful")
    public void completeDelivery(UUID orderId) {
        deliveryService.completeDelivery(orderId);
    }

    @Override
    @PostMapping("/picked")
    public void confirmPickup(UUID orderId) {
        deliveryService.confirmPickup(orderId);
    }

    @Override
    @PostMapping("/failed")
    public void failDelivery(UUID orderId) {
        deliveryService.failDelivery(orderId);
    }

    @Override
    @PostMapping("/cost")
    public BigDecimal calculateDeliveryCost(OrderDto orderDto) {
        return deliveryService.calculateDeliveryCost(orderDto);
    }
}
