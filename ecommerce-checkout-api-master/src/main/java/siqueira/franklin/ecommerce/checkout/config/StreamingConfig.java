package siqueira.franklin.ecommerce.checkout.config;

import siqueira.franklin.ecommerce.checkout.streaming.CheckoutCreatedSource;
import siqueira.franklin.ecommerce.checkout.streaming.PaymentPaidSink;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding(value = {
        CheckoutCreatedSource.class,
        PaymentPaidSink.class
})
public class StreamingConfig {
}
