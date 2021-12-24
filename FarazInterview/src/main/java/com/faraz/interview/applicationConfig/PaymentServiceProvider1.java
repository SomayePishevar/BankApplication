package com.faraz.interview.applicationConfig;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentServiceProvider1 {
    private final String url = "https://first-payment-provider/payments/transfer";
}
