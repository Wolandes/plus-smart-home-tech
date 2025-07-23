package ru.yandex.practicum.mapper;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.model.Payment;
import ru.yandex.practicum.model.PaymentDto;

import java.math.BigDecimal;

/**
 * Маппер платежа
 */
@Component
public class PaymentMapper {

    public PaymentDto toPaymentDto(Payment payment){
        return PaymentDto.builder()
                .paymentId(payment.getPaymentId())
                .totalPayment(payment.getTotalPayment().doubleValue())
                .deliveryTotal(payment.getDeliveryTotal().doubleValue())
                .feeTotal(payment.getFeeTotal().doubleValue())
                .build();
    }
}
