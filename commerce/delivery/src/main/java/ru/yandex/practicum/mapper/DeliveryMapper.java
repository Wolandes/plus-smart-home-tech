package ru.yandex.practicum.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.model.Delivery;
import ru.yandex.practicum.model.DeliveryDto;

/**
 * Маппер доставки
 */
@Component
@RequiredArgsConstructor
public class DeliveryMapper {
    private final AdressMapper adressMapper;

    public Delivery toDelivery(DeliveryDto deliveryDto){
        return Delivery.builder()
                .deliveryId(deliveryDto.getDeliveryId())
                .orderId(deliveryDto.getOrderId())
                .senderAddress(adressMapper.toAddress(deliveryDto.getSenderAddress()))
                .recipientAddress(adressMapper.toAddress(deliveryDto.getRecipientAddress()))
                .deliveryStatus(deliveryDto.getDeliveryState())
                .build();
    }

    public DeliveryDto toDeliveryDto(Delivery delivery){
        return DeliveryDto.builder()
                .deliveryId(delivery.getDeliveryId())
                .orderId(delivery.getOrderId())
                .senderAddress(adressMapper.toAddressDto(delivery.getSenderAddress()))
                .recipientAddress(adressMapper.toAddressDto(delivery.getRecipientAddress()))
                .deliveryState(delivery.getDeliveryStatus())
                .build();
    }
}
