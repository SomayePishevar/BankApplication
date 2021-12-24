package com.faraz.interview.applicationConfig;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SMSServiceProvider {

    private final String url = "https://first-payment-provider/payments/transfer";
    private String textMessage = "sms text";
}
