package siqueira.franklin.ecommerce.payment.service;

import siqueira.franklin.ecommerce.checkout.event.CheckoutCreatedEvent;
import siqueira.franklin.ecommerce.payment.entity.PaymentEntity;

import java.util.Optional;

public interface PaymentService {

    Optional<PaymentEntity> create(CheckoutCreatedEvent checkoutCreatedEvent);
}