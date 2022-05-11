package mk.ukim.finki.hardwareshop.service;


import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import mk.ukim.finki.hardwareshop.model.ChargeRequest;

public interface StripeService {
    void init();
    Charge charge(ChargeRequest chargeRequest) throws StripeException;
}
