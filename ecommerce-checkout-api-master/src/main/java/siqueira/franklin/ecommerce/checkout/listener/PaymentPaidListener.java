package siqueira.franklin.ecommerce.checkout.listener;

import siqueira.franklin.ecommerce.checkout.entity.CheckoutEntity;
import siqueira.franklin.ecommerce.checkout.service.CheckoutService;
import siqueira.franklin.ecommerce.checkout.streaming.PaymentPaidSink;
import siqueira.franklin.ecommerce.payment.event.PaymentCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentPaidListener {

    private final CheckoutService checkoutService;

    @StreamListener(PaymentPaidSink.INPUT)
    public void handler(PaymentCreatedEvent paymentCreatedEvent) {
        checkoutService
                .updateStatus(paymentCreatedEvent
                        .getCheckoutCode().toString(), CheckoutEntity
                        .Status.APPROVED);
    }
}
