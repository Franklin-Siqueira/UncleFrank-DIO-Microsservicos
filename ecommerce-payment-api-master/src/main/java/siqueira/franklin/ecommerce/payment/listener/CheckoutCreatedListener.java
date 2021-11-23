package siqueira.franklin.ecommerce.payment.listener;

import siqueira.franklin.ecommerce.checkout.event.CheckoutCreatedEvent;
import siqueira.franklin.ecommerce.payment.entity.PaymentEntity;
import siqueira.franklin.ecommerce.payment.event.PaymentCreatedEvent;
import siqueira.franklin.ecommerce.payment.service.PaymentService;
import siqueira.franklin.ecommerce.payment.streaming.CheckoutProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CheckoutCreatedListener {

    private final CheckoutProcessor checkoutProcessor;
    private final PaymentService paymentService;

    @StreamListener(CheckoutProcessor.INPUT)
    public void handler(CheckoutCreatedEvent checkoutCreatedEvent) {
        log.info("checkoutCreatedEvent={}", checkoutCreatedEvent);
        final PaymentEntity paymentEntity = paymentService
                .create(checkoutCreatedEvent)
                .orElseThrow();
        final PaymentCreatedEvent paymentCreatedEvent = PaymentCreatedEvent
                .newBuilder()
                .setCheckoutCode(paymentEntity.getCheckoutCode())
                .setPaymentCode(paymentEntity.getCode())
                .build();
        checkoutProcessor
                .output()
                .send(MessageBuilder
                        .withPayload(paymentCreatedEvent)
                        .build());
    }
}
