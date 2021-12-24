package com.faraz.interview.applicationConfig;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentServiceProvider2 {
    private final String url = "https://second-payment-provider/cards/pay";
}
