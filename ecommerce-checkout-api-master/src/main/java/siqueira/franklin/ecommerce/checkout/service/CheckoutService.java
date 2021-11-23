package siqueira.franklin.ecommerce.checkout.service;

import siqueira.franklin.ecommerce.checkout.entity.CheckoutEntity;
import siqueira.franklin.ecommerce.checkout.resource.CheckoutRequest;

import java.util.Optional;

public interface CheckoutService {

    Optional<CheckoutEntity> create(CheckoutRequest checkoutRequest);

    Optional<CheckoutEntity> updateStatus(String checkoutCode, CheckoutEntity.Status status);
}
